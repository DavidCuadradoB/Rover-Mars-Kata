package org.david.cb.infrastructure.controller.exception;

public class IncorrectCommandException extends Exception {
    public IncorrectCommandException(Character character) {
        super("The command : " + character + " is not a valid character.");
    }
}
