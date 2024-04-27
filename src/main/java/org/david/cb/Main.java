package org.david.cb;

import org.david.cb.application.deploy.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.newmissionusecase.NewMissionUseCase;
import org.david.cb.infrastructure.commandreader.TerminalCommandReader;
import org.david.cb.infrastructure.commandwritter.TerminalPositionWriter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IncorrectCommandForPlateauLimitsException {
        TerminalCommandReader terminalCommandReader = new TerminalCommandReader(new Scanner(System.in));
        TerminalPositionWriter terminalPositionWriter = new TerminalPositionWriter();
        DeployMowerService deployMowerService = new DeployMowerService(terminalCommandReader, terminalPositionWriter);
        CreatePlateauService createPlateauService = new CreatePlateauService(terminalCommandReader);
        NewMissionUseCase newMissionUseCase =
                new NewMissionUseCase(createPlateauService, deployMowerService, terminalCommandReader);

        newMissionUseCase.execute();
    }
}