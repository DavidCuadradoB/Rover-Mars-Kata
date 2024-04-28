package org.david.cb;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.exceptions.IncorrectCommandForMowerInitialOrientationException;
import org.david.cb.application.mower.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.infrastructure.commandreader.TerminalMowerCommandReader;
import org.david.cb.infrastructure.commandreader.TerminalNewMissionCommandReader;
import org.david.cb.infrastructure.commandreader.TerminalPlateauCommandReader;
import org.david.cb.infrastructure.commandwritter.TerminalPositionWriter;
import org.david.cb.infrastructure.controller.NewMissionController;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws IncorrectCommandForPlateauLimitsException, IncorrectPlateauLimitsException, IncorrectCommandForMowerInitialOrientationException {
        Scanner scanner = new Scanner(System.in);
        TerminalPlateauCommandReader terminalPlateauCommandReader = new TerminalPlateauCommandReader(scanner);
        TerminalPositionWriter terminalPositionWriter = new TerminalPositionWriter();
        TerminalMowerCommandReader terminalMowerCommandReader = new TerminalMowerCommandReader(scanner);
        TerminalNewMissionCommandReader terminalNewMissionCommandReader = new TerminalNewMissionCommandReader(scanner);
        DeployMowerService deployMowerService = new DeployMowerService();
        CreatePlateauService createPlateauService = new CreatePlateauService();

        NewMissionController newMissionController = new NewMissionController(
                createPlateauService,
                deployMowerService,
                terminalNewMissionCommandReader,
                terminalMowerCommandReader,
                terminalPlateauCommandReader,
                terminalPositionWriter
        );

        newMissionController.execute();
    }
}