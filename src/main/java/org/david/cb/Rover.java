package org.david.cb;

public class Rover {

    private final Coordinates coordinates;
    private final Orientation orientation;

    public Rover(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Rover moveForward() {
        if (this.orientation == Orientation.NORTH) {
            return new Rover(new Coordinates(coordinates.getX(), coordinates.getY() + 1), this.orientation);
        } else if (this.orientation == Orientation.EAST) {
            return new Rover(new Coordinates(coordinates.getX() + 1, coordinates.getY()), this.orientation);
        } else if (this.orientation == Orientation.SOUTH) {
            return new Rover(new Coordinates(coordinates.getX(), coordinates.getY() - 1), this.orientation);
        } else {
            return new Rover(new Coordinates(coordinates.getX() - 1, coordinates.getY()), this.orientation);
        }
    }

    public Rover rotateRight() {
        if(Orientation.NORTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.EAST);
        } else if (Orientation.EAST == this.orientation) {
            return new Rover(this.coordinates, Orientation.SOUTH);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.WEST);
        } else {
            return new Rover(this.coordinates, Orientation.NORTH);
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
