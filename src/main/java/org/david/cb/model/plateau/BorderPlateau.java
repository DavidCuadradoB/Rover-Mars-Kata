package org.david.cb.model.plateau;

import org.david.cb.model.Coordinates;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorderPlateau implements Plateau {

    public static final int SOUTH_LIMIT = 0;
    public static final int WEST_LIMIT = 0;
    private final int eastLimit;
    private final int northLimit;

    private final List<Coordinates> obstacles = new ArrayList<>();

    public BorderPlateau(int eastLimit, int northLimit) throws IncorrectPlateauLimitsException {
        if (eastLimit <= 0 || northLimit <= 0) {
            throw new IncorrectPlateauLimitsException(eastLimit, northLimit);
        }
        this.eastLimit = eastLimit;
        this.northLimit = northLimit;

    }

    @Override
    public Coordinates calculatePosition(Coordinates currentCoordinates, Orientation orientation) {
        if (Orientation.NORTH == orientation) {
            return getCoordinatesFacingNorth(currentCoordinates);
        } else if (Orientation.EAST == orientation) {
            return getCoordinatesFacingEast(currentCoordinates);
        } else if (Orientation.SOUTH == orientation) {
            return getCoordinatesFacingSouth(currentCoordinates);
        } else {
            return getCoordinatesFacingWest(currentCoordinates);
        }
    }

    @Override
    public void addObstacle(Coordinates coordinates) {
        this.obstacles.add(coordinates);
    }

    @Override
    public boolean checkCoordinates(Coordinates coordinates) {
        if (
                coordinates.getX() > eastLimit ||
                        coordinates.getY() > northLimit ||
                        SOUTH_LIMIT > coordinates.getY() ||
                        WEST_LIMIT > coordinates.getX() ||
                        obstacles.contains(coordinates)
        ) {
            return false;
        }
        return true;
    }

    private Coordinates getCoordinatesFacingWest(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX() - 1, currentCoordinates.getY());
        if (targetCoordinates.getX() < SOUTH_LIMIT || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    private Coordinates getCoordinatesFacingSouth(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);
        if (targetCoordinates.getY() < WEST_LIMIT || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    private Coordinates getCoordinatesFacingEast(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
        if (targetCoordinates.getX() > this.eastLimit || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    private Coordinates getCoordinatesFacingNorth(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
        if (targetCoordinates.getY() > this.northLimit || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorderPlateau that = (BorderPlateau) o;
        return eastLimit == that.eastLimit && northLimit == that.northLimit && Objects.equals(obstacles, that.obstacles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eastLimit, northLimit, obstacles);
    }
}
