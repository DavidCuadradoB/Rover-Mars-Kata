package org.david.cb.model.mower.exception;

public class IncorrectInitialPositionException extends Exception {
    public IncorrectInitialPositionException(int x, int y) {
        super(String.format("The initial: %s, %s position is incorrect.", x, y));
    }

}
