package org.david.cb.mower;

public class IncorrectCommandException extends Exception {
    public IncorrectCommandException(Character character) {
        super("The command : " + character + " is not a valid character.");
    }
}
