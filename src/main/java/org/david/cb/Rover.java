package org.david.cb;

public class Rover {

    private final Coordinates coordinates;
    private final Orientation orientation;
    private final Plateau plateau;

    public Rover(Coordinates coordinates, Orientation orientation, Plateau plateau) {
        this.coordinates = coordinates;
        this.orientation = orientation;
        this.plateau = plateau;
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
            return new Rover(
                    plateau.calculatePosition(this.coordinates, this.orientation),
                    this.orientation,
                    this.plateau
            );
        } else if (Orientation.EAST == this.orientation) {
            return new Rover(
                    plateau.calculatePosition(this.coordinates, this.orientation),
                    this.orientation,
                    this.plateau
            );
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(
                    plateau.calculatePosition(this.coordinates, this.orientation),
                    this.orientation,
                    this.plateau
            );
        } else {
            return new Rover(
                    plateau.calculatePosition(this.coordinates, this.orientation),
                    this.orientation,
                    this.plateau
            );
        }
    }

    private Rover rotateRight() {
        if (Orientation.NORTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.EAST, this.plateau);
        } else if (Orientation.EAST == this.orientation) {
            return new Rover(this.coordinates, Orientation.SOUTH, this.plateau);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.WEST, this.plateau);
        } else {
            return new Rover(this.coordinates, Orientation.NORTH, this.plateau);
        }
    }

    private Rover rotateLeft() {
        if (Orientation.NORTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.WEST, this.plateau);
        } else if (Orientation.WEST == this.orientation) {
            return new Rover(this.coordinates, Orientation.SOUTH, this.plateau);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Rover(this.coordinates, Orientation.EAST, this.plateau);
        } else {
            return new Rover(this.coordinates, Orientation.NORTH, this.plateau);
        }
    }
}
