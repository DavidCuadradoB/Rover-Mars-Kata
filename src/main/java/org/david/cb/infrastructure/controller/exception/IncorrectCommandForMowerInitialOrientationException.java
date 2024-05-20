package org.david.cb.infrastructure.controller.exception;

public class IncorrectCommandForMowerInitialOrientationException extends Exception {
    public IncorrectCommandForMowerInitialOrientationException(String string) {
        super("The command: " + string + " is not a valid command for mower initial position.");
    }

}
