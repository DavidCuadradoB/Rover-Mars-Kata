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
    void rover_orientation_north_moveForward_north_should_move_to_north() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.NORTH);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y + 1);
    }

    @Test
    void rover_orientation_east_moveForward_east_should_move_to_east() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.EAST);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x + 1);
        Assertions.assertEquals(coordinates.getY(), y);
    }

    @Test
    void rover_orientation_south_moveForward_south_should_move_to_south() {
        int y = 5;
        int x = 5;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.SOUTH);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y-1);
    }

    @Test
    void rover_orientation_west_moveForward_west_should_move_to_west() {
        int y = 5;
        int x = 5;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.WEST);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x-1);
        Assertions.assertEquals(coordinates.getY(), y);
    }

    @Test
    void rover_orientation_north_when_rotate_right_should_rotate_east() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.NORTH);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(Orientation.EAST, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_east_when_rotate_right_should_rotate_south() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.EAST);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(Orientation.SOUTH, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_south_when_rotate_right_should_rotate_west() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.SOUTH);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(Orientation.WEST, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_west_when_rotate_right_should_rotate_north() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.WEST);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(Orientation.NORTH, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_north_when_rotate_left_should_rotate_west() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.NORTH);
        Rover rotatedRover = rover.rotateLeft();
        Assertions.assertEquals(Orientation.WEST, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_west_when_rotate_left_should_rotate_south() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.WEST);
        Rover rotatedRover = rover.rotateLeft();
        Assertions.assertEquals(Orientation.SOUTH, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_south_when_rotate_left_should_rotate_east() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.SOUTH);
        Rover rotatedRover = rover.rotateLeft();
        Assertions.assertEquals(Orientation.EAST, rotatedRover.getOrientation());
    }


}