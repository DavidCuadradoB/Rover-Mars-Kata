package org.david.cb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.david.cb.Orientation.*;

class RoverTest {

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_coordinates() {
        Coordinates coordinates = new Coordinates(5, 7);
        Rover rover = new Rover(coordinates, EAST);
        Assertions.assertNotNull(rover);
        Assertions.assertEquals(coordinates, rover.getCoordinates());
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
    void execute_with_move_when_the_rover_is_facing_north_the_rover_should_move_to_north() {
        Rover rover = getRover(NORTH);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), rover.getCoordinates().getX());
        Assertions.assertEquals(coordinates.getY(), rover.getCoordinates().getY() + 1);
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_east_the_rover_should_move_to_east() {
        Rover rover = getRover(EAST);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), rover.getCoordinates().getX() + 1);
        Assertions.assertEquals(coordinates.getY(), rover.getCoordinates().getY());
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_south_the_rover_should_move_to_south() {
        Rover rover = getRover(SOUTH);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), rover.getCoordinates().getX());
        Assertions.assertEquals(coordinates.getY(), rover.getCoordinates().getY() - 1);
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_west_the_rover_should_move_to_west() {
        Rover rover = getRover(WEST);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), rover.getCoordinates().getX() - 1);
        Assertions.assertEquals(coordinates.getY(), rover.getCoordinates().getY());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_north_the_rover_should_rotate_to_east() {
        Rover rover = getRover(NORTH);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(EAST, movedRover.getOrientation());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_east_the_rover_should_rotate_to_south() {
        Rover rover = getRover(EAST);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(SOUTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_south_the_rover_should_rotate_to_west() {
        Rover rover = getRover(SOUTH);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(WEST, movedRover.getOrientation());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_west_the_rover_should_rotate_to_north() {
        Rover rover = getRover(WEST);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Assertions.assertEquals(NORTH, movedRover.getOrientation());
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