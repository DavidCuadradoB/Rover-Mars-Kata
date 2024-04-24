package org.david.cb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"M", "m"})
    void execute_with_move_when_the_rover_is_facing_north_the_rover_should_move_to_north(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(NORTH);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX(),
                rover.getCoordinates().getY() + 1
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @ParameterizedTest
    @ValueSource(strings = {"M", "m"})
    void execute_with_move_when_the_rover_is_facing_east_the_rover_should_move_to_east(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(EAST);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX() + 1,
                rover.getCoordinates().getY()
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @ParameterizedTest
    @ValueSource(strings = {"M", "m"})
    void execute_with_move_when_the_rover_is_facing_south_the_rover_should_move_to_south(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(SOUTH);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX(),
                rover.getCoordinates().getY() - 1
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @ParameterizedTest
    @ValueSource(strings = {"M", "m"})
    void execute_with_move_when_the_rover_is_facing_west_the_rover_should_move_to_west(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(WEST);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        Coordinates expectedCoordinates = new Coordinates(
                rover.getCoordinates().getX() - 1,
                rover.getCoordinates().getY()
        );
        assertEquals(expectedCoordinates, movedRover.getCoordinates());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_rover_is_facing_north_the_rover_should_rotate_to_east(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(NORTH);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(EAST, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_rover_is_facing_east_the_rover_should_rotate_to_south(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(EAST);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(SOUTH, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_rover_is_facing_south_the_rover_should_rotate_to_west(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(SOUTH);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(WEST, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_rover_is_facing_west_the_rover_should_rotate_to_north(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(WEST);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(NORTH, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_rover_is_facing_north_the_rover_should_rotate_to_west(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(NORTH);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(WEST, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_rover_is_facing_west_the_rover_should_rotate_to_south(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(WEST);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(SOUTH, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_rover_is_facing_south_the_rover_should_rotate_to_east(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(SOUTH);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(EAST, movedRover.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_rover_is_facing_east_the_rover_should_rotate_to_north(String commands)
            throws IncorrectCommandException {
        Rover rover = getRover(EAST);
        MoveCommand command = new MoveCommand(commands);

        Rover movedRover = rover.execute(command);

        assertEquals(NORTH, movedRover.getOrientation());
    }

    @Test
    void execute_with_incorrect_value_should_throw_an_exception() {
        Rover rover = getRover(EAST);
        String commands = "/";
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