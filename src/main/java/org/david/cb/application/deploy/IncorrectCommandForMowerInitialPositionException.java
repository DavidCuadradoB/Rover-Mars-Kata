package org.david.cb.application.deploy;

public class IncorrectCommandForMowerInitialPositionException extends Exception {
    public IncorrectCommandForMowerInitialPositionException(String string) {
        super("The command: " + string + " is not a valid command for mower initial position.");
    }

}
