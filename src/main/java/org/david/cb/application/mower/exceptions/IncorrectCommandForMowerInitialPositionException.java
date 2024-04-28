package org.david.cb.application.mower.exceptions;

public class IncorrectCommandForMowerInitialPositionException extends Exception {
    public IncorrectCommandForMowerInitialPositionException(String string) {
        super("The command: " + string + " is not a valid command for mower initial position.");
    }

}
