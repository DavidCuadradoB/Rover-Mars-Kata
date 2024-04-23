package org.david.cb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoverTest {

    @Test
    void create_should_work() {
        Coordinates coordinates = new Coordinates(0, 0);
        Orientation orientation = Orientation.NORTH;
        Rover rover = new Rover(coordinates, orientation);
        Assertions.assertNotNull(rover);
        Assertions.assertEquals(coordinates, rover.getCoordinates());
        Assertions.assertEquals(orientation, rover.getOrientation());
    }

    @Test
    void moveForward_north_should_move_to_north() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.NORTH);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y + 1);
    }

    @Test
    void moveForward_east_should_move_to_east() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.EAST);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x + 1);
        Assertions.assertEquals(coordinates.getY(), y);
    }

    @Test
    void moveForward_south_should_move_to_south() {
        int y = 5;
        int x = 5;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.SOUTH);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y-1);
    }

    @Test
    void rotate_right_should_rotate_east() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.NORTH);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(Orientation.EAST, rotatedRover.getOrientation());
    }
}