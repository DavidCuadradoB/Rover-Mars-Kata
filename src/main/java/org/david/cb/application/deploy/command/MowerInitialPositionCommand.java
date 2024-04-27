package org.david.cb.application.deploy.command;

public class MowerInitialPositionCommand {

    private final String x;
    private final String y;
    private final String orientation;


    public MowerInitialPositionCommand(String x, String y, String orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}
