package org.david.cb.infrastructure.commandreader;

import org.david.cb.model.commandreader.PlateauLimitsCommandReader;

import java.util.Scanner;

public class TerminalPlateauLimitsCommandReader implements PlateauLimitsCommandReader {

    private final Scanner scanner;

    public TerminalPlateauLimitsCommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readPlateauLimits(String question) {
        System.out.println(question);

        return scanner.nextLine();
    }
}
