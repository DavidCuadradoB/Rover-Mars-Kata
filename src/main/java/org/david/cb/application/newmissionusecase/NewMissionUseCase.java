package org.david.cb.application.newmissionusecase;

import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.plateau.Plateau;

public class NewMissionUseCase {

    private final CreatePlateauService createPlateauService;
    private final DeployMowerService deployMowerService;
    private final CommandReader commandReader;
    private final PositionWriter positionWriter;

    public NewMissionUseCase(
            CreatePlateauService createPlateauService,
            DeployMowerService deployMowerService,
            CommandReader commandReader,
            PositionWriter positionWriter
    ) {
        this.createPlateauService = createPlateauService;
        this.deployMowerService = deployMowerService;
        this.commandReader = commandReader;
        this.positionWriter = positionWriter;
    }

    public void execute() throws IncorrectCommandForPlateauLimitsException
    {
        Plateau plateau = createPlateauService.createPlateau();
        do {
            deployMowerService.deploy(plateau).ifPresent(
                    mower -> positionWriter.write(
                            mower.getCoordinates().getX() + " " +
                                    mower.getCoordinates().getY() + " " +
                                    mower.getOrientation().abbreviation
                    )
            );


        } while(
                !commandReader.readCommand("type 'exit' to exit the program or enter to add a new mower")
                        .equals("exit")
        );

    }
}
