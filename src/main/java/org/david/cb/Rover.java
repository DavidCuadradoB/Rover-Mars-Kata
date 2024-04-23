package org.david.cb;

public class Rover {

    private final Coordinates coordinates;

    public Rover(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Rover moveForward() {
        Coordinates newCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() +1);
        return new Rover(newCoordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
