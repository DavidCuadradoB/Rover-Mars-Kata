package org.david.cb;

public class BorderPlateau implements Plateau {

    private final int limitX;
    private final int limitY;

    public BorderPlateau(int limitX, int limitY) {
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public Coordinates calculatePosition(Coordinates currentCoordinates, Orientation orientation) {
        if (Orientation.NORTH == orientation) {
            if (currentCoordinates.getY() + 1 >= limitY) {
                return currentCoordinates;
            }
            return new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
        } else if (Orientation.EAST == orientation) {
            if (currentCoordinates.getX() + 1 >= limitX) {
                return currentCoordinates;
            }
            return new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
        } else if (Orientation.SOUTH == orientation) {
            if (currentCoordinates.getY() - 1 < 0) {
                return currentCoordinates;
            }
            return new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);
        } else {
            return new Coordinates(currentCoordinates.getX() - 1, currentCoordinates.getY());
        }
    }

    public int getLimitX() {
        return limitX;
    }

    public int getLimitY() {
        return limitY;
    }
}
