package org.david.cb.model.mower;

import org.david.cb.model.Coordinates;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.Plateau;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Mower {

    private Coordinates coordinates;
    private Orientation orientation;
    private final Plateau plateau;
    private final UUID id = UUID.randomUUID();

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
        for (MowerCommand command : commands) {
            if (MowerCommand.ROTATE_LEFT.equals(command)) {
                this.rotateLeft();
            } else if (MowerCommand.ROTATE_RIGHT.equals(command)) {
                this.rotateRight();
            } else if (MowerCommand.MOVE_FORWARD.equals(command)) {
                this.moveForward();
            }
        }
        plateau.addObstacle(this.coordinates);
        return this;
    }

    private void moveForward() {
        this.coordinates=plateau.calculatePosition(this.coordinates, this.orientation);
    }

    private void rotateRight() {
        if (Orientation.NORTH == this.orientation) {
            this.orientation=Orientation.EAST;
        } else if (Orientation.EAST == this.orientation) {
            this.orientation=Orientation.SOUTH;
        } else if (Orientation.SOUTH == this.orientation) {
            this.orientation=Orientation.WEST;
        } else {
            this.orientation=Orientation.NORTH;
        }
    }

    private void rotateLeft() {
        if (Orientation.NORTH == this.orientation) {
            this.orientation=Orientation.WEST;
        } else if (Orientation.WEST == this.orientation) {
            this.orientation=Orientation.SOUTH;
        } else if (Orientation.SOUTH == this.orientation) {
            this.orientation=Orientation.EAST;
        } else {
            this.orientation=Orientation.NORTH;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mower mower = (Mower) o;
        return Objects.equals(id, mower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
