package org.david.cb;

public class Mower {

    private final Coordinates coordinates;
    private final Orientation orientation;
    private final Plateau plateau;

    public Mower(Coordinates coordinates, Orientation orientation, Plateau plateau) {
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

    public Mower execute(MoveCommand command) throws IncorrectCommandException {

        Mower moved_mower = this;

        for (char c : command.getCommands().toCharArray()) {
            char commandUpperCase = Character.toUpperCase(c);
            if (commandUpperCase == 'L') {
                moved_mower = rotateLeft();
            } else if (commandUpperCase == 'R') {
                moved_mower = rotateRight();
            } else if (commandUpperCase == 'M') {
                moved_mower = moveForward();
            } else {
                throw new IncorrectCommandException(c);
            }
        }

        return moved_mower;
    }

    private Mower moveForward() {
        return new Mower(
                plateau.calculatePosition(this.coordinates, this.orientation),
                this.orientation,
                this.plateau
        );
    }

    private Mower rotateRight() {
        if (Orientation.NORTH == this.orientation) {
            return new Mower(this.coordinates, Orientation.EAST, this.plateau);
        } else if (Orientation.EAST == this.orientation) {
            return new Mower(this.coordinates, Orientation.SOUTH, this.plateau);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Mower(this.coordinates, Orientation.WEST, this.plateau);
        } else {
            return new Mower(this.coordinates, Orientation.NORTH, this.plateau);
        }
    }

    private Mower rotateLeft() {
        if (Orientation.NORTH == this.orientation) {
            return new Mower(this.coordinates, Orientation.WEST, this.plateau);
        } else if (Orientation.WEST == this.orientation) {
            return new Mower(this.coordinates, Orientation.SOUTH, this.plateau);
        } else if (Orientation.SOUTH == this.orientation) {
            return new Mower(this.coordinates, Orientation.EAST, this.plateau);
        } else {
            return new Mower(this.coordinates, Orientation.NORTH, this.plateau);
        }
    }
}
