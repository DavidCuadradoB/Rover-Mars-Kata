package org.david.cb;

import java.util.ArrayList;
import java.util.List;

public class BorderPlateau implements Plateau {

    private final int limitX;
    private final int limitY;

    private final List<Coordinates> obstacles = new ArrayList<>();

    public BorderPlateau(int limitX, int limitY) {
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public Coordinates calculatePosition(Coordinates currentCoordinates, Orientation orientation) {
        if (Orientation.NORTH == orientation) {
            Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
            if (targetCoordinates.getY() > limitY || this.obstacles.contains(targetCoordinates)) {
                return currentCoordinates;
            }
            return targetCoordinates;
        } else if (Orientation.EAST == orientation) {
            Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
            if (targetCoordinates.getX() > limitX || this.obstacles.contains(targetCoordinates)) {
                return currentCoordinates;
            }
            return targetCoordinates;
        } else if (Orientation.SOUTH == orientation) {
            Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);
            if (currentCoordinates.getY() - 1 < 0 || this.obstacles.contains(targetCoordinates)) {
                return currentCoordinates;
            }
            return targetCoordinates;
        } else {
            Coordinates targetCoordinates = new Coordinates(currentCoordinates.getX() - 1, currentCoordinates.getY());
            if (currentCoordinates.getX() - 1 < 0 || this.obstacles.contains(targetCoordinates)) {
                return currentCoordinates;
            }
            return targetCoordinates;
        }
    }

    @Override
    public void addObstacle(Coordinates coordinates) {
        this.obstacles.add(coordinates);
    }

    public int getLimitX() {
        return limitX;
    }

    public int getLimitY() {
        return limitY;
    }
}
