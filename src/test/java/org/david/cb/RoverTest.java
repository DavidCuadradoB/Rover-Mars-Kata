package org.david.cb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RoverTest {

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_coordinates() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = Orientation.EAST;
        Rover rover = new Rover(coordinates, orientation);
        Assertions.assertNotNull(rover);
        Assertions.assertEquals(coordinates, rover.getCoordinates());
    }

    @Test
    void rover_execute_left_should_rotate_left() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = Orientation.EAST;
        Rover rover = new Rover(coordinates, orientation);

        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(Orientation.NORTH, movedRover.getOrientation());
    }

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_orientation() {
        Coordinates coordinates = new Coordinates(2, 3);
        Orientation orientation = Orientation.SOUTH;
        Rover rover = new Rover(coordinates, orientation);
        Assertions.assertNotNull(rover);
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
        Assertions.assertEquals(coordinates.getY(), y - 1);
    }

    @Test
    void rover_orientation_west_moveForward_west_should_move_to_west() {
        int y = 5;
        int x = 5;
        Rover rover = new Rover(new Coordinates(x, y), Orientation.WEST);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x - 1);
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
    void execute_with_left_when_the_rover_is_facing_north_the_rover_should_rotate_to_west() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = Orientation.NORTH;
        Rover rover = new Rover(coordinates, orientation);

        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(Orientation.WEST, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_west_the_rover_should_rotate_to_south() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = Orientation.WEST;
        Rover rover = new Rover(coordinates, orientation);

        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(Orientation.SOUTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_south_the_rover_should_rotate_to_east() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = Orientation.SOUTH;
        Rover rover = new Rover(coordinates, orientation);

        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(Orientation.EAST, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_east_the_rover_should_rotate_to_north() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = Orientation.EAST;
        Rover rover = new Rover(coordinates, orientation);

        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(Orientation.NORTH, movedRover.getOrientation());
    }
}