package org.david.cb.model.plateau.exception;

public class IncorrectPlateauLimitsException extends Exception {
    public IncorrectPlateauLimitsException(int x, int y) {
        super(String.format("The plateau limits: %s, %s are incorrect.", x, y));
    }

}
