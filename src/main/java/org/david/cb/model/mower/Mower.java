package org.david.cb.model.mower;

import org.david.cb.model.Coordinates;
import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.model.plateau.Plateau;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;

import java.util.List;

public class Mower {

    private final Coordinates coordinates;
    private final Orientation orientation;
    private final Plateau plateau;

    public Mower(Coordinates coordinates, Orientation orientation, Plateau plateau) throws IncorrectInitialCoordinatesException {
        if (plateau.checkCoordinates(coordinates)) {
            this.coordinates = coordinates;
            this.orientation = orientation;
            this.plateau = plateau;
        } else {
            throw new IncorrectInitialCoordinatesException(coordinates.getX(), coordinates.getY());
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Mower execute(List<MowerCommand> commands) throws IncorrectInitialCoordinatesException {

        Mower moved_mower = this;

        for (MowerCommand command : commands) {
            if (MowerCommand.ROTATE_LEFT.equals(command)) {
                moved_mower = moved_mower.rotateLeft();
            } else if (MowerCommand.ROTATE_RIGHT.equals(command)) {
                moved_mower = moved_mower.rotateRight();
            } else if (MowerCommand.MOVE_FORWARD.equals(command)) {
                moved_mower = moved_mower.moveForward();
            }
        }
        plateau.addObstacle(moved_mower.coordinates);

        return moved_mower;
    }

    private Mower moveForward() throws IncorrectInitialCoordinatesException {
        return new Mower(
                plateau.calculatePosition(this.coordinates, this.orientation),
                this.orientation,
                this.plateau
        );
    }

    private Mower rotateRight() throws IncorrectInitialCoordinatesException {
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

    private Mower rotateLeft() throws IncorrectInitialCoordinatesException {
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
