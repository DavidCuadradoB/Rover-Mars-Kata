package org.david.cb;

import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.mower.Mower;
import org.david.cb.mower.MowerCommand;
import org.david.cb.mower.Orientation;
import org.david.cb.plateau.Plateau;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.david.cb.mower.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerTest {

    public static final int X = 2;
    public static final int Y = 2;
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

    @Test
    void execute_should_calculatePosition_using_the_plateau() throws IncorrectCommandException {
        Mower mower = getMower(EAST);
        List<MowerCommand> commands = List.of(MowerCommand.MOVE_FORWARD);

        Coordinates expectedCoordinates = new Coordinates(1, 1);
        when(plateau.calculatePosition(mower.getCoordinates(), mower.getOrientation())).thenReturn(expectedCoordinates);

        Mower movedMower = mower.execute(commands);

        assertEquals(movedMower.getCoordinates(), expectedCoordinates);

    }

    @Test
    void execute_should_add_an_obstacle_to_grid_when_finish_the_movement() throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        List<MowerCommand> commands = List.of(MowerCommand.MOVE_FORWARD);

        Coordinates newCoordinates = new Coordinates(1, 1);
        when(plateau.calculatePosition(mower.getCoordinates(), mower.getOrientation())).thenReturn(newCoordinates);

        mower.execute(commands);

        verify(plateau).addObstacle(newCoordinates);
    }

    @Test
    void execute_should_move_the_mower()
            throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        List<MowerCommand> commands = List.of(
                MowerCommand.MOVE_FORWARD,
                MowerCommand.MOVE_FORWARD
        );

        Coordinates firstCoordinates = new Coordinates(1, 1);
        Coordinates secondCoordinates = new Coordinates(1, 2);
        when(plateau.calculatePosition(mower.getCoordinates(), mower.getOrientation()))
                .thenReturn(firstCoordinates, secondCoordinates);

        Mower movedMower = mower.execute(commands);

        verify(plateau).calculatePosition(mower.getCoordinates(), mower.getOrientation());
        verify(plateau).calculatePosition(firstCoordinates, mower.getOrientation());
    }

    @Test
    void execute_with_right_when_the_mower_is_facing_north_the_mower_should_rotate_to_east()
            throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_RIGHT);

        Mower movedMower = mower.execute(commands);

        assertEquals(EAST, movedMower.getOrientation());
    }

    @Test
    void execute_with_right_when_the_mower_is_facing_east_the_mower_should_rotate_to_south()
            throws IncorrectCommandException {
        Mower mower = getMower(EAST);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_RIGHT);

        Mower movedMower = mower.execute(commands);

        assertEquals(SOUTH, movedMower.getOrientation());
    }

    @Test
    void execute_with_right_when_the_mower_is_facing_south_the_mower_should_rotate_to_west()
            throws IncorrectCommandException {
        Mower mower = getMower(SOUTH);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_RIGHT);

        Mower movedMower = mower.execute(commands);

        assertEquals(WEST, movedMower.getOrientation());
    }

    @Test
    void execute_with_right_when_the_mower_is_facing_west_the_mower_should_rotate_to_north()
            throws IncorrectCommandException {
        Mower mower = getMower(WEST);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_RIGHT);

        Mower movedMower = mower.execute(commands);

        assertEquals(NORTH, movedMower.getOrientation());
    }

    @Test
    void execute_with_left_when_the_mower_is_facing_north_the_mower_should_rotate_to_west()
            throws IncorrectCommandException {
        Mower mower = getMower(NORTH);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_LEFT);

        Mower movedMower = mower.execute(commands);

        assertEquals(WEST, movedMower.getOrientation());
    }

    @Test
    void execute_with_left_when_the_mower_is_facing_west_the_mower_should_rotate_to_south()
            throws IncorrectCommandException {
        Mower mower = getMower(WEST);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_LEFT);

        Mower movedMower = mower.execute(commands);

        assertEquals(SOUTH, movedMower.getOrientation());
    }

    @Test
    void execute_with_left_when_the_mower_is_facing_south_the_mower_should_rotate_to_east()
            throws IncorrectCommandException {
        Mower mower = getMower(SOUTH);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_LEFT);

        Mower movedMower = mower.execute(commands);

        assertEquals(EAST, movedMower.getOrientation());
    }

    @Test
    void execute_with_left_when_the_mower_is_facing_east_the_mower_should_rotate_to_north()
            throws IncorrectCommandException {
        Mower mower = getMower(EAST);
        List<MowerCommand> commands = List.of(MowerCommand.ROTATE_LEFT);

        Mower movedMower = mower.execute(commands);

        assertEquals(NORTH, movedMower.getOrientation());
    }

    private Mower getMower(Orientation orientation) {
        Coordinates coordinates = new Coordinates(X, Y);
        return new Mower(coordinates, orientation, plateau);
    }
}