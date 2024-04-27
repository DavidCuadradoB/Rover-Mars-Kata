package org.david.cb.application.deploy;

import org.david.cb.Coordinates;
import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.commandreader.CommandReader;
import org.david.cb.commandwriter.PositionWriter;
import org.david.cb.mower.Mower;
import org.david.cb.mower.MowerCommand;
import org.david.cb.mower.Orientation;
import org.david.cb.mower.exception.IncorrectInitialPositionException;
import org.david.cb.plateau.BorderPlateau;
import org.david.cb.plateau.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeployMowerService  {

    private final CommandReader commandReader;
    private final PositionWriter positionWriter;

    public DeployMowerService(CommandReader commandReader, PositionWriter positionWriter) {
        this.commandReader = commandReader;
        this.positionWriter = positionWriter;
    }

    public void deploy() throws
            IncorrectCommandException,
            IncorrectCommandForPlateauLimitsException,
            IncorrectCommandForMowerInitialPositionException,
            IncorrectInitialPositionException
    {

        Plateau plateau = getPlateau();
        while (true) {
            Mower mower = getMower(plateau);
            List<MowerCommand> mowerCommands = new ArrayList<>();

            for (char c : commandReader.readCommand().toCharArray()) {
                mowerCommands.add(MowerCommand.fromChar(c).orElseThrow(() -> new IncorrectCommandException(c)));
            }

            Mower movedMower = mower.execute(mowerCommands);

            positionWriter.write(
                    movedMower.getCoordinates().getX() + " " +
                            movedMower.getCoordinates().getY() + " " +
                            movedMower.getOrientation().abbreviation
            );
        }


    }

    private Mower getMower(Plateau plateau) throws IncorrectCommandForMowerInitialPositionException, IncorrectInitialPositionException {
        String mowerInitialPosition = commandReader.readCommand();
        String regexMowerInitialPosition = "(\\d) (\\d) (\\w)";

        Pattern patternMowerInitialPosition = Pattern.compile(regexMowerInitialPosition);
        Matcher matcherMowerInitialPosition = patternMowerInitialPosition.matcher(mowerInitialPosition);

        if (matcherMowerInitialPosition.find()) {
            Coordinates initialCoordinates = new Coordinates(
                    Integer.parseInt(matcherMowerInitialPosition.group(1)),
                    Integer.parseInt(matcherMowerInitialPosition.group(2))
            );

            Orientation orientation = Orientation.forAbbreviation(matcherMowerInitialPosition.group(3))
                    .orElseThrow(() -> new IncorrectCommandForMowerInitialPositionException(mowerInitialPosition));

            return new Mower(initialCoordinates, orientation, plateau);
        }
        throw new IncorrectCommandForMowerInitialPositionException(mowerInitialPosition);
    }

    private Plateau getPlateau() throws IncorrectCommandForPlateauLimitsException {
        String plateauLimitsRaw = commandReader.readCommand();
        String regexPlateauLimits = "(\\d) (\\d)";

        Pattern patternPlateauLimits = Pattern.compile(regexPlateauLimits);
        Matcher matcherPlateauLimits = patternPlateauLimits.matcher(plateauLimitsRaw);

        if (matcherPlateauLimits.find()) {
            return new BorderPlateau(
                    Integer.parseInt(matcherPlateauLimits.group(1)),
                    Integer.parseInt(matcherPlateauLimits.group(2))
            );
        } else {
            throw new IncorrectCommandForPlateauLimitsException(plateauLimitsRaw);
        }
    }
}
