package org.david.cb.model.mower;

public enum MowerCommand {

    ROTATE_RIGHT('R'),
    ROTATE_LEFT('L'),
    MOVE_FORWARD('M');

    public final char command;

    MowerCommand(char command) {
        this.command = command;
    }

}
