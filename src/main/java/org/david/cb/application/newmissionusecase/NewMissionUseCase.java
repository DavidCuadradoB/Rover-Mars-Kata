package org.david.cb.application.newmissionusecase;

import org.david.cb.application.deploy.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.model.commandreader.CommandReader;

public class NewMissionUseCase {

    private final CreatePlateauService createPlateauService;
    private final DeployMowerService deployMowerService;
    private final CommandReader commandReader;

    public NewMissionUseCase(CreatePlateauService createPlateauService, DeployMowerService deployMowerService, CommandReader commandReader, CommandReader commandReader1) {
        this.createPlateauService = createPlateauService;
        this.deployMowerService = deployMowerService;
        this.commandReader = commandReader1;
    }

    public void execute() throws IncorrectCommandForPlateauLimitsException {

        createPlateauService.createPlateau();
    }
}
