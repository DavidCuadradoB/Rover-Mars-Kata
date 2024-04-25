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
            return new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
        } else if (Orientation.EAST == orientation) {
            return new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
        } else if (Orientation.SOUTH == orientation) {
            return new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);
        }

        return null;
    }

    public int getLimitX() {
        return limitX;
    }

    public int getLimitY() {
        return limitY;
    }
}
