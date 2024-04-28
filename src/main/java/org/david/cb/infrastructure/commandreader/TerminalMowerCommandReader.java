package org.david.cb.infrastructure.commandreader;

import org.david.cb.model.commandreader.MowerCommandReader;

import java.util.Scanner;

public class TerminalMowerCommandReader implements MowerCommandReader {

    private final Scanner scanner;

    public TerminalMowerCommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readMowerInitialPositionCommands() {
        System.out.println("Insert the Mower initial position in the format: X Y [N, S, E, W]");

        return scanner.nextLine();
    }

    @Override
    public String readMowerMovementCommands() {
        System.out.println("Insert the Mower movements commands");

        return scanner.nextLine();
    }
}
