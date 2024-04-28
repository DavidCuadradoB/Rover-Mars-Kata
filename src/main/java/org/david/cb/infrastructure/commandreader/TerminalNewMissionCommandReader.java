package org.david.cb.infrastructure.commandreader;

import org.david.cb.model.commandreader.NewMissionCommandReader;

import java.util.Scanner;

public class TerminalNewMissionCommandReader implements NewMissionCommandReader {

    private final Scanner scanner;

    public TerminalNewMissionCommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readExit() {
        System.out.println("type 'exit' to exit the program or enter to add a new mower");

        return scanner.nextLine();
    }
}
