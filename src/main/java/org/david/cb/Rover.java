package org.david.cb;

public class Rover {

    private final Coordinates coordinates;
    private final Orientation orientation;

    public Rover(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Rover execute(MoveCommand command) throws IncorrectCommandException {

        Rover moved_rover = this;

        for (char c : command.getCommands().toCharArray()) {
            char commandUpperCase = Character.toUpperCase(c);
            if (commandUpperCase == 'L') {
                moved_rover = rotateLeft();
            } else if (commandUpperCase == 'R') {
                moved_rover = rotateRight();
            } else if (commandUpperCase == 'M') {
                moved_rover = moveForward();
            } else {
                throw new IncorrectCommandException(c);
            }
        }

        return moved_rover;
    }

    private Rover moveForward() {
        if (Orientation.NORTH == this.orientation) {
            return new Rover(new Coordinates(coordinates.getX(), coordinates.getY() + 1), this.orientation);
        } else if (Orientation.EAST == this.orientation) {
            return new Rover(new Coordinates(coordinates.getX() + 1, coordinates.getY()), this.orientation);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(new Coordinates(coordinates.getX(), coordinates.getY() - 1), this.orientation);
        } else {
            return new Rover(new Coordinates(coordinates.getX() - 1, coordinates.getY()), this.orientation);
        }
    }

    private Rover rotateRight() {
        if (Orientation.NORTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.EAST);
        } else if (Orientation.EAST == this.orientation) {
            return new Rover(this.coordinates, Orientation.SOUTH);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.WEST);
        } else {
            return new Rover(this.coordinates, Orientation.NORTH);
        }
    }

    private Rover rotateLeft() {
        if (Orientation.NORTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.WEST);
        } else if (Orientation.WEST == this.orientation) {
            return new Rover(this.coordinates, Orientation.SOUTH);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.EAST);
        } else {
            return new Rover(this.coordinates, Orientation.NORTH);
        }
    }
}
