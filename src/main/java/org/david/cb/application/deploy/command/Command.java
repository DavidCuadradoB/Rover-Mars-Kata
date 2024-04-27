package org.david.cb.application.deploy.command;

public class Command {


    private final PlateauLimitsCommand plateauLimitsCommand;
    private final MowerInitialPositionCommand mowerInitialPositionCommand;
    private final String movement;

    public Command(
            PlateauLimitsCommand plateauLimitsCommand,
            MowerInitialPositionCommand mowerInitialPositionCommand,
            String movement
    ) {
        this.plateauLimitsCommand = plateauLimitsCommand;
        this.mowerInitialPositionCommand = mowerInitialPositionCommand;
        this.movement = movement;
    }

    public PlateauLimitsCommand getGridLimitsCommand() {
        return plateauLimitsCommand;
    }

    public MowerInitialPositionCommand getMowerInitialPositionMower() {
        return mowerInitialPositionCommand;
    }

    public String getMovement() {
        return movement;
    }
}
