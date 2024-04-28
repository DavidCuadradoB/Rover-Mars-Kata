package org.david.cb.application.mower.exceptions;

public class IncorrectCommandForMowerInitialOrientationException extends Exception {
    public IncorrectCommandForMowerInitialOrientationException(String string) {
        super("The command: " + string + " is not a valid command for mower initial position.");
    }

}
