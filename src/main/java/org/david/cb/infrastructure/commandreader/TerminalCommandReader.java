package org.david.cb.infrastructure.commandreader;

import org.david.cb.model.commandreader.CommandReader;

import java.util.Scanner;

public class TerminalCommandReader implements CommandReader {

    private final Scanner scanner;

    public TerminalCommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readCommand() {
        System.out.println("Get value: ");
        String input = scanner.nextLine();

        return input;


    }
}
