package org.david.cb.model.mower;

import java.util.Optional;

public enum Orientation {

    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    public final String abbreviation;

    Orientation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static Optional<Orientation> forAbbreviation(String abbreviation) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.abbreviation.equals(abbreviation)) {
                return Optional.of(orientation);
            }
        }
        return Optional.empty();

    }

}
