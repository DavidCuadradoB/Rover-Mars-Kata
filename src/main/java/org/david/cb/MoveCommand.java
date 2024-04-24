package org.david.cb;

public class MoveCommand {

    private final String commands;

    public MoveCommand(String commands) {
        this.commands = commands;
    }

    public String getCommands() {
        return this.commands;
    }
}
