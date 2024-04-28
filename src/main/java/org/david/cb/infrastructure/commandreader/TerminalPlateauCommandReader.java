package org.david.cb.infrastructure.commandreader;

import org.david.cb.model.commandreader.PlateauCommandReader;

import java.util.Scanner;

public class TerminalPlateauCommandReader implements PlateauCommandReader {

    private final Scanner scanner;

    public TerminalPlateauCommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readPlateauLimits(String question) {
        System.out.println(question);

        return scanner.nextLine();
    }
}
