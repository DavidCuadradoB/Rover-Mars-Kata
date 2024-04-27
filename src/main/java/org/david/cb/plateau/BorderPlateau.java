package org.david.cb.plateau;

import org.david.cb.Coordinates;
import org.david.cb.mower.Orientation;

import java.util.ArrayList;
import java.util.List;

public class BorderPlateau implements Plateau {

    public static final int BOTTOM_LIMIT = 0;
    public static final int LEFT_LIMIT = 0;
    private final int leftLimit;
    private final int topLimit;

    private final List<Coordinates> obstacles = new ArrayList<>();

    public BorderPlateau(int leftLimit, int topLimit) {
        this.leftLimit = leftLimit;
        this.topLimit = topLimit;
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

    private Coordinates getCoordinatesFacingWest(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX() - 1, currentCoordinates.getY());
        if (targetCoordinates.getX() < BOTTOM_LIMIT || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    private Coordinates getCoordinatesFacingSouth(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);
        if (targetCoordinates.getY() < LEFT_LIMIT || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    private Coordinates getCoordinatesFacingEast(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
        if (targetCoordinates.getX() > this.leftLimit || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

    private Coordinates getCoordinatesFacingNorth(Coordinates currentCoordinates) {
        Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
        if (targetCoordinates.getY() > this.topLimit || this.obstacles.contains(targetCoordinates)) {
            return currentCoordinates;
        }
        return targetCoordinates;
    }

}
