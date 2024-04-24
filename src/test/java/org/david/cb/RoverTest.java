package org.david.cb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.david.cb.Orientation.*;

class RoverTest {

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_coordinates() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = EAST;
        Rover rover = new Rover(coordinates, orientation);
        Assertions.assertNotNull(rover);
        Assertions.assertEquals(coordinates, rover.getCoordinates());
    }

    @Test
    void rover_execute_left_should_rotate_left() {
        Coordinates coordinates = new Coordinates(5, 7);
        Orientation orientation = EAST;
        Rover rover = new Rover(coordinates, orientation);

        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(NORTH, movedRover.getOrientation());
    }

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_orientation() {
        Coordinates coordinates = new Coordinates(2, 3);
        Orientation orientation = SOUTH;
        Rover rover = new Rover(coordinates, orientation);
        Assertions.assertNotNull(rover);
        Assertions.assertEquals(orientation, rover.getOrientation());
    }

    @Test
    void rover_orientation_north_moveForward_north_should_move_to_north() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), NORTH);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y + 1);
    }

    @Test
    void rover_orientation_east_moveForward_east_should_move_to_east() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y), EAST);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x + 1);
        Assertions.assertEquals(coordinates.getY(), y);
    }

    @Test
    void rover_orientation_south_moveForward_south_should_move_to_south() {
        int y = 5;
        int x = 5;
        Rover rover = new Rover(new Coordinates(x, y), SOUTH);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y - 1);
    }

    @Test
    void rover_orientation_west_moveForward_west_should_move_to_west() {
        int y = 5;
        int x = 5;
        Rover rover = new Rover(new Coordinates(x, y), WEST);
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x - 1);
        Assertions.assertEquals(coordinates.getY(), y);
    }

    @Test
    void rover_orientation_north_when_rotate_right_should_rotate_east() {
        Rover rover = getRover(NORTH);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(EAST, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_east_when_rotate_right_should_rotate_south() {
        Rover rover = getRover(EAST);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(SOUTH, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_south_when_rotate_right_should_rotate_west() {
        Rover rover = getRover(SOUTH);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(WEST, rotatedRover.getOrientation());
    }

    @Test
    void rover_orientation_west_when_rotate_right_should_rotate_north() {
        Rover rover = getRover(WEST);
        Rover rotatedRover = rover.rotateRight();
        Assertions.assertEquals(NORTH, rotatedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_north_the_rover_should_rotate_to_west() {
        Rover rover = getRover(NORTH);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(WEST, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_west_the_rover_should_rotate_to_south() {
        Rover rover = getRover(WEST);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(SOUTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_south_the_rover_should_rotate_to_east() {
        Rover rover = getRover(SOUTH);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(EAST, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_east_the_rover_should_rotate_to_north() {
        Rover rover = getRover(EAST);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(NORTH, movedRover.getOrientation());
    }


    private static Rover getRover(Orientation orientation) {
        Coordinates coordinates = new Coordinates(5, 7);
        return new Rover(coordinates, orientation);
    }
}