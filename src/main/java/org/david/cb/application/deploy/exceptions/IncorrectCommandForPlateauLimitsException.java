package org.david.cb.application.deploy.exceptions;

public class IncorrectCommandForPlateauLimitsException extends Exception {
    public IncorrectCommandForPlateauLimitsException(String string) {
        super("The command: " + string + " is not a valid command for plateau limits.");
    }

}
