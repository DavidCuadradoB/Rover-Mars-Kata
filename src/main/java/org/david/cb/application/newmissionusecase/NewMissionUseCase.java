package org.david.cb.application.newmissionusecase;

import org.david.cb.application.deploy.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.Plateau;

public class NewMissionUseCase {

    private final CreatePlateauService createPlateauService;
    private final DeployMowerService deployMowerService;
    private final CommandReader commandReader;

    public NewMissionUseCase(
            CreatePlateauService createPlateauService,
            DeployMowerService deployMowerService,
            CommandReader commandReader
    ) {
        this.createPlateauService = createPlateauService;
        this.deployMowerService = deployMowerService;
        this.commandReader = commandReader;
    }

    public void execute() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException,
            IncorrectCommandForMowerInitialPositionException,
            IncorrectCommandException
    {

        do {
            Plateau plateau = createPlateauService.createPlateau();
            deployMowerService.deploy(plateau);
        } while(
                !commandReader.readCommand("type 'exit' to exit the program or enter to add a new mower")
                        .equals("exit")
        );

    }
}
