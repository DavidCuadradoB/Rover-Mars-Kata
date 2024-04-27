package org.david.cb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.david.cb.Orientation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerTest {

    public static final int X = 5;
    public static final int Y = 7;
    @Mock
    private Plateau plateau;

    @Test
    void mower_create_mower_should_be_created_in_the_indicated_coordinates() {
        Coordinates coordinates = new Coordinates(5, 7);
        Mower mower = new Mower(coordinates, EAST, plateau);
        assertNotNull(mower);
        assertEquals(coordinates, mower.getCoordinates());
    }

    @Test
    void mower_create_mower_should_be_created_in_the_indicated_orientation() {
        Coordinates coordinates = new Coordinates(2, 3);
        Orientation orientation = SOUTH;
        Mower mower = new Mower(coordinates, orientation, plateau);
        assertNotNull(mower);
        assertEquals(orientation, mower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"M", "m"})
    void execute_should_calculatePosition_using_the_plateau(String command) throws IncorrectCommandException {
        Mower mower = getMower(EAST);
        MoveCommand commands = new MoveCommand(command);

        Coordinates expectedCoordinates = new Coordinates(1, 1);
        when(plateau.calculatePosition(mower.getCoordinates(), mower.getOrientation())).thenReturn(expectedCoordinates);

        Mower movedMower = mower.execute(commands);

        assertEquals(movedMower.getCoordinates(), expectedCoordinates);

    }

    @Test
    void execute_should_add_an_obstacle_to_grid_when_finish_the_movement() throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        String commands = "M";
        MoveCommand command = new MoveCommand(commands);

        Coordinates newCoordinates = new Coordinates(1, 1);
        when(plateau.calculatePosition(mower.getCoordinates(), mower.getOrientation())).thenReturn(newCoordinates);

        mower.execute(command);

        verify(plateau).addObstacle(newCoordinates);
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_mower_is_facing_north_the_mower_should_rotate_to_east(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(EAST, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_mower_is_facing_east_the_mower_should_rotate_to_south(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(EAST);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(SOUTH, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_mower_is_facing_south_the_mower_should_rotate_to_west(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(SOUTH);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(WEST, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void execute_with_right_when_the_mower_is_facing_west_the_mower_should_rotate_to_north(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(WEST);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(NORTH, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_mower_is_facing_north_the_mower_should_rotate_to_west(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(WEST, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_mower_is_facing_west_the_mower_should_rotate_to_south(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(WEST);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(SOUTH, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_mower_is_facing_south_the_mower_should_rotate_to_east(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(SOUTH);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(EAST, movedMower.getOrientation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "l"})
    void execute_with_left_when_the_mower_is_facing_east_the_mower_should_rotate_to_north(String commands)
            throws IncorrectCommandException {
        Mower mower = getMower(EAST);
        MoveCommand command = new MoveCommand(commands);

        Mower movedMower = mower.execute(command);

        assertEquals(NORTH, movedMower.getOrientation());
    }

    @Test
    void execute_with_incorrect_value_should_throw_an_exception() {
        Mower mower = getMower(EAST);
        String commands = "/";
        MoveCommand command = new MoveCommand(commands);

        IncorrectCommandException exception = assertThrows(
                IncorrectCommandException.class, () -> mower.execute(command)
        );

        String expectedMessage = "The command : " + commands + " is not a valid character.";

        assertEquals(expectedMessage, exception.getMessage());
    }

    private Mower getMower(Orientation orientation) {
        Coordinates coordinates = new Coordinates(X, Y);
        return new Mower(coordinates, orientation, plateau);
    }
}