package org.david.cb;

import org.junit.jupiter.api.Test;

import static org.david.cb.Orientation.*;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_coordinates() {
        Coordinates coordinates = new Coordinates(5, 7);
        Rover rover = new Rover(coordinates, EAST);
        assertNotNull(rover);
        assertEquals(coordinates, rover.getCoordinates());
    }

    @Test
    void rover_create_rover_should_be_created_in_the_indicated_orientation() {
        Coordinates coordinates = new Coordinates(2, 3);
        Orientation orientation = SOUTH;
        Rover rover = new Rover(coordinates, orientation);
        assertNotNull(rover);
        assertEquals(orientation, rover.getOrientation());
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_north_the_rover_should_move_to_north() throws IncorrectCommandException {
        Rover rover = getRover(NORTH);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX(),
                rover.getCoordinates().getY() + 1
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_east_the_rover_should_move_to_east() throws IncorrectCommandException {
        Rover rover = getRover(EAST);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX() + 1,
                rover.getCoordinates().getY()
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_south_the_rover_should_move_to_south() throws IncorrectCommandException {
        Rover rover = getRover(SOUTH);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX(),
                rover.getCoordinates().getY() - 1
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @Test
    void execute_with_move_when_the_rover_is_facing_west_the_rover_should_move_to_west() throws IncorrectCommandException {
        Rover rover = getRover(WEST);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX() - 1,
                rover.getCoordinates().getY()
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_north_the_rover_should_rotate_to_east() throws IncorrectCommandException {
        Rover rover = getRover(NORTH);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(EAST, movedRover.getOrientation());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_east_the_rover_should_rotate_to_south() throws IncorrectCommandException {
        Rover rover = getRover(EAST);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(SOUTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_south_the_rover_should_rotate_to_west() throws IncorrectCommandException {
        Rover rover = getRover(SOUTH);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(WEST, movedRover.getOrientation());
    }

    @Test
    void execute_with_right_when_the_rover_is_facing_west_the_rover_should_rotate_to_north() throws IncorrectCommandException {
        Rover rover = getRover(WEST);
        String commands = "R";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(NORTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_north_the_rover_should_rotate_to_west() throws IncorrectCommandException {
        Rover rover = getRover(NORTH);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(WEST, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_west_the_rover_should_rotate_to_south() throws IncorrectCommandException {
        Rover rover = getRover(WEST);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(SOUTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_south_the_rover_should_rotate_to_east() throws IncorrectCommandException {
        Rover rover = getRover(SOUTH);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(EAST, movedRover.getOrientation());
    }

    @Test
    void execute_with_left_when_the_rover_is_facing_east_the_rover_should_rotate_to_north() throws IncorrectCommandException {
        Rover rover = getRover(EAST);
        String commands = "L";
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(NORTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_incorrect_value_should_throw_an_exception() {
        Rover rover = getRover(EAST);
        String commands = "I";
        MoveCommand command = new MoveCommand(commands);

        IncorrectCommandException exception = assertThrows(
                IncorrectCommandException.class, () -> rover.execute(command)
        );

        String expectedMessage = "The command : " + commands + " is not a valid character.";

        assertEquals(expectedMessage, exception.getMessage());
    }


    private static Rover getRover(Orientation orientation) {
        Coordinates coordinates = new Coordinates(5, 7);
        return new Rover(coordinates, orientation);
    }
}