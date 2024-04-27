package org.david.cb.model.mower;

import java.util.Optional;

public enum MowerCommand {

    ROTATE_RIGHT('R'),
    ROTATE_LEFT('L'),
    MOVE_FORWARD('M');

    public final char command;

    MowerCommand(char command) {
        this.command = command;
    }

    public static Optional<MowerCommand> fromChar(char command) {
        for (MowerCommand mowerCommand : MowerCommand.values()) {
            if (mowerCommand.command == command) {
                return Optional.of(mowerCommand);
            }
        }
        return Optional.empty();
    }

}
