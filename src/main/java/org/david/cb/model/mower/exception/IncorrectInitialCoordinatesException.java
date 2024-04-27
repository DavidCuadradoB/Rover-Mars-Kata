package org.david.cb.model.mower.exception;

public class IncorrectInitialCoordinatesException extends Exception {
    public IncorrectInitialCoordinatesException(int x, int y) {
        super(String.format("The initial: %s, %s position is incorrect.", x, y));
    }

}
