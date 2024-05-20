package org.david.cb.infrastructure.controller;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.infrastructure.controller.exception.IncorrectCommandForMowerInitialOrientationException;
import org.david.cb.application.mower.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.application.plateau.command.CreatePlateauCommand;
import org.david.cb.model.commandreader.MowerCommandReader;
import org.david.cb.model.commandreader.NewMissionCommandReader;
import org.david.cb.model.commandreader.PlateauCommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.plateau.Plateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class NewMissionController {

    private static final String REGEX_PLATEAU_LIMITS = "(\\d+)\\s+(\\d+)";
    private static final String REGEX_MOWER_INITIAL_POSITION = "(\\d*) (\\d*) (\\w)";

    private final CreatePlateauService createPlateauService;
    private final DeployMowerService deployMowerService;
    private final NewMissionCommandReader newMissionCommandReader;
    private final PositionWriter positionWriter;
    private final MowerCommandReader mowerCommandReader;
    private final PlateauCommandReader plateauCommandReader;
    private final Logger logger = LoggerFactory.getLogger(NewMissionController.class);


    public NewMissionController(
            CreatePlateauService createPlateauService,
            DeployMowerService deployMowerService,
            NewMissionCommandReader newMissionCommandReader,
            MowerCommandReader mowerCommandReader,
            PlateauCommandReader plateauCommandReader,
            PositionWriter positionWriter
    ) {
        this.createPlateauService = createPlateauService;
        this.deployMowerService = deployMowerService;
        this.newMissionCommandReader = newMissionCommandReader;
        this.positionWriter = positionWriter;
        this.mowerCommandReader = mowerCommandReader;
        this.plateauCommandReader = plateauCommandReader;
    }

    public void execute() throws IncorrectCommandForPlateauLimitsException, IncorrectPlateauLimitsException {
        Plateau plateau = createPlateauService.createPlateau(getCreatePlateauCommand());
        do {
            try {
                deployMowerService.deploy(getCreateMowerCommand(), getMowerCommandReader(), plateau)
                        .ifPresent(mower -> positionWriter.write(
                                        mower.getCoordinates().getX() + " " +
                                                mower.getCoordinates().getY() + " " +
                                                mower.getOrientation().abbreviation
                                )
                        );
            } catch (IncorrectCommandForMowerInitialOrientationException exception) {
                logger.error("Incorrect command for mower initial orientation.", exception);
            }

        } while (
                !newMissionCommandReader.readExit().equals("exit")
        );

    }

    private CreatePlateauCommand getCreatePlateauCommand() throws IncorrectCommandForPlateauLimitsException {
        String plateauLimitsRaw = plateauCommandReader.readPlateauLimits();
        Matcher matcherPlateauLimits = compile(REGEX_PLATEAU_LIMITS).matcher(plateauLimitsRaw);

        if (matcherPlateauLimits.find()) {
            return new CreatePlateauCommand(
                    Integer.parseInt(matcherPlateauLimits.group(1)),
                    Integer.parseInt(matcherPlateauLimits.group(2))
            );
        } else {
            throw new IncorrectCommandForPlateauLimitsException(plateauLimitsRaw);
        }
    }

    private CreateMowerCommand getCreateMowerCommand() throws IncorrectCommandForMowerInitialOrientationException {
        String mowerInitialPosition = mowerCommandReader.readMowerInitialPositionCommands();
        Matcher matcherMowerInitialPosition = compile(REGEX_MOWER_INITIAL_POSITION).matcher(mowerInitialPosition);

        if (matcherMowerInitialPosition.find()) {
            return new CreateMowerCommand(
                    Integer.parseInt(matcherMowerInitialPosition.group(1)),
                    Integer.parseInt(matcherMowerInitialPosition.group(2)),
                    fromCommand(matcherMowerInitialPosition.group(3))
            );
        }
        throw new IncorrectCommandForMowerInitialOrientationException(mowerInitialPosition);
    }

    private MowerMovementCommand getMowerCommandReader() {
        return new MowerMovementCommand(mowerCommandReader.readMowerMovementCommands());
    }

    private Orientation fromCommand(String orientation)
            throws IncorrectCommandForMowerInitialOrientationException {
        switch (orientation) {
            case "N" -> {
                return Orientation.NORTH;
            }
            case "E" -> {
                return Orientation.EAST;
            }
            case "S" -> {
                return Orientation.SOUTH;
            }
            case "W" -> {
                return Orientation.WEST;
            }
            default ->
                    throw new IncorrectCommandForMowerInitialOrientationException(orientation);
        }
    }

}
