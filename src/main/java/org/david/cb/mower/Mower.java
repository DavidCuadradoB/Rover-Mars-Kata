package org.david.cb.mower;

import org.david.cb.Coordinates;
import org.david.cb.plateau.Plateau;

import java.util.List;

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

    public Mower execute(List<MowerCommand> commands) throws IncorrectCommandException {

        Mower moved_mower = this;

        for (MowerCommand command : commands) {
            if (MowerCommand.ROTATE_LEFT.equals(command)) {
                moved_mower = rotateLeft();
            } else if (MowerCommand.ROTATE_RIGHT.equals(command)) {
                moved_mower = rotateRight();
            } else if (MowerCommand.MOVE_FORWARD.equals(command)) {
                moved_mower = moveForward();
            }
        }
        plateau.addObstacle(moved_mower.coordinates);

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
