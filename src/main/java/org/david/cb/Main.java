package org.david.cb;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.newmissionusecase.NewMissionUseCase;
import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.infrastructure.commandreader.TerminalMowerCommandReader;
import org.david.cb.infrastructure.commandreader.TerminalNewMissionCommandReader;
import org.david.cb.infrastructure.commandreader.TerminalPlateauCommandReader;
import org.david.cb.infrastructure.commandwritter.TerminalPositionWriter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IncorrectCommandForPlateauLimitsException {
        Scanner scanner = new Scanner(System.in);
        TerminalPlateauCommandReader terminalCommandReader = new TerminalPlateauCommandReader(scanner);
        TerminalPositionWriter terminalPositionWriter = new TerminalPositionWriter();
        TerminalMowerCommandReader terminalMowerCommandReader = new TerminalMowerCommandReader(scanner);
        TerminalNewMissionCommandReader terminalNewMissionCommandReader = new TerminalNewMissionCommandReader(scanner);
        DeployMowerService deployMowerService = new DeployMowerService(terminalMowerCommandReader);
        CreatePlateauService createPlateauService = new CreatePlateauService(terminalCommandReader);

        NewMissionUseCase newMissionUseCase = new NewMissionUseCase(
                createPlateauService,
                deployMowerService,
                terminalNewMissionCommandReader,
                terminalPositionWriter
        );

        newMissionUseCase.execute();
    }
}