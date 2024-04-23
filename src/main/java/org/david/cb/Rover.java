package org.david.cb;

public class Rover {

    private final Coordinates coordinates;
    private final Orientation orientation;

    public Rover(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Rover moveForward() {
        Coordinates newCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() +1);
        return new Rover(newCoordinates, this.orientation);
    }

    public Rover rotateRight() {
        return new Rover(this.coordinates, Orientation.EAST);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
