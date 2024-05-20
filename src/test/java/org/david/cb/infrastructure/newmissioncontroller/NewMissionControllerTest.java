package org.david.cb.infrastructure.newmissioncontroller;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.application.mower.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.application.plateau.command.CreatePlateauCommand;
import org.david.cb.infrastructure.controller.NewMissionController;
import org.david.cb.model.Coordinates;
import org.david.cb.model.commandreader.MowerCommandReader;
import org.david.cb.model.commandreader.NewMissionCommandReader;
import org.david.cb.model.commandreader.PlateauCommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.MowerCommand;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewMissionControllerTest {

    @Mock
    private DeployMowerService deployMowerService;

    @Mock
    private CreatePlateauService createPlateauService;

    @Mock
    private NewMissionCommandReader newMissionCommandReader;

    @Mock
    private PlateauCommandReader plateauCommandReader;

    @Mock
    private MowerCommandReader mowerCommandReader;

    @Mock
    private PositionWriter positionWriter;

    @InjectMocks
    private NewMissionController newMissionController;


    @Test
    void execute_should_create_a_new_plateau()
            throws IncorrectCommandForPlateauLimitsException, IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 5;
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(limitX, limitY);
        BorderPlateau plateau = new BorderPlateau(limitX, limitY);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("5 5");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("1 1 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");

        newMissionController.execute();
        verify(createPlateauService).createPlateau(createPlateauCommand);
    }

    @Test
    void execute_should_move_forward_a_new_mower() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("M");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                List.of(
                        MowerCommand.MOVE_FORWARD
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_rotate_left_new_mower() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("L");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                List.of(
                        MowerCommand.ROTATE_LEFT
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_throw_an_exception_when_movement_command_are_incorrect() throws
            IncorrectPlateauLimitsException,
            IncorrectCommandForPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("/");
        when(newMissionCommandReader.readExit()).thenReturn("exit");

        newMissionController.execute();

        verify(deployMowerService, never()).deploy(any(), any(), any());
    }

    @Test
    void execute_should_rotate_right_new_mower() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("R");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                List.of(
                        MowerCommand.ROTATE_RIGHT
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_deploy_a_new_mower_facing_north() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_deploy_a_new_mower_facing_east() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 E");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.EAST);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_deploy_a_new_mower_facing_south() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 S");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.SOUTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_deploy_a_new_mower_facing_west() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 W");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, Orientation.WEST);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_not_deploy_an_mower_if_orientation_of_commands_for_initial_position_are_incorrect() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(10, 10);

        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(5 ,5 );

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("5 5");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("1 2 /");
        when(newMissionCommandReader.readExit()).thenReturn("exit");

        newMissionController.execute();

        verify(deployMowerService, never()).deploy(any(), any(), any());
    }

    @Test
    void execute_should_print_mower_position() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException,
            IncorrectPlateauLimitsException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        Mower mower = new Mower(new Coordinates(15, 13), Orientation.NORTH, plateau);

        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100 );
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 13, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 13 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");
        when(deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau))
                .thenReturn(Optional.of(mower));

        newMissionController.execute();
        verify(positionWriter).write(
                mower.getCoordinates().getX() + " " +
                        mower.getCoordinates().getY() + " " +
                        mower.getOrientation().abbreviation
        );

    }

    @Test
    void execute_should_exit_loop_until_user_exit() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException,
            IncorrectInitialCoordinatesException
    {
        int eastLimit = 10;
        int northLimit = 10;
        BorderPlateau plateau = new BorderPlateau(eastLimit, northLimit);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(eastLimit, northLimit);
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1, 1, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );
        Mower mower = new Mower(new Coordinates(1, 1), Orientation.NORTH, plateau);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("10 10");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("1 1 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("", "exit");
        when(deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau))
                .thenReturn(Optional.of(mower));

        newMissionController.execute();
        verify(createPlateauService, times(1)).createPlateau(createPlateauCommand);
        verify(deployMowerService, times(2))
                .deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_not_create_plateau_if_commands_are_incorrect() throws IncorrectPlateauLimitsException {
        when(plateauCommandReader.readPlateauLimits()).thenReturn("a a");

        IncorrectCommandForPlateauLimitsException exception = assertThrows(
                IncorrectCommandForPlateauLimitsException.class, () -> newMissionController.execute()
        );

        assertEquals("The command: a a is not a valid command for plateau limits.", exception.getMessage());

        verify(createPlateauService, never()).createPlateau(any());
    }

    @Test
    void execute_should_not_deploy_an_mower_if_commands_for_initial_position_are_incorrect() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(10, 10);

        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(5 ,5 );

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("5 5");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("a b N");
        when(newMissionCommandReader.readExit()).thenReturn("exit");

        newMissionController.execute();

        verify(deployMowerService, never()).deploy(any(), any(), any());
    }
}