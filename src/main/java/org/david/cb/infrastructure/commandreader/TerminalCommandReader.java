package org.david.cb.infrastructure.commandreader;

import org.david.cb.commandreader.CommandReader;

import java.util.Scanner;

public class TerminalCommandReader implements CommandReader {

    private final Scanner scanner;

    public TerminalCommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readCommand() {
        return scanner.next();
    }
}