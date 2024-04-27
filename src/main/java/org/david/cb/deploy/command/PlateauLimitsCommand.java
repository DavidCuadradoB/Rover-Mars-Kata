package org.david.cb.deploy.command;

public class PlateauLimitsCommand {

    private final String plateauLimitX;
    private final String plateauLimitY;

    public PlateauLimitsCommand(String plateauLimitX, String plateauLimitY) {
        this.plateauLimitX = plateauLimitX;
        this.plateauLimitY = plateauLimitY;
    }

    public String getPlateauLimitX() {
        return plateauLimitX;
    }

    public String getPlateauLimitY() {
        return plateauLimitY;
    }
}
