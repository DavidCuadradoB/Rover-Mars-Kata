package org.david.cb.mower;

public enum Orientation {

    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    public final String abbreviation;

    Orientation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
