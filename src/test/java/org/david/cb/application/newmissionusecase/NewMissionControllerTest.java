package org.david.cb.application.newmissionusecase;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.application.mower.exceptions.IncorrectCommandForMowerInitialOrientationException;
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
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            throws IncorrectCommandForPlateauLimitsException, IncorrectPlateauLimitsException, IncorrectCommandForMowerInitialOrientationException {
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
    void execute_should_deploy_a_new_mower() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException,
            IncorrectCommandForMowerInitialOrientationException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100);

        when(createPlateauService.createPlateau(createPlateauCommand)).thenReturn(plateau);
        when(plateauCommandReader.readPlateauLimits()).thenReturn("100 100");
        when(mowerCommandReader.readMowerInitialPositionCommands()).thenReturn("15 35 N");
        when(mowerCommandReader.readMowerMovementCommands()).thenReturn("MMM");
        when(newMissionCommandReader.readExit()).thenReturn("exit");


        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 35, "N");
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand("MMM");

        newMissionController.execute();
        verify(deployMowerService).deploy(createMowerCommand, mowerMovementCommand, plateau);
    }

    @Test
    void execute_should_print_mower_position() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException,
            IncorrectPlateauLimitsException, IncorrectCommandForMowerInitialOrientationException {
        BorderPlateau plateau = new BorderPlateau(100, 100);
        Mower mower = new Mower(new Coordinates(15, 13), Orientation.NORTH, plateau);

        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(100 ,100 );
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(15, 13, "N");
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand("MMM");

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
            IncorrectCommandForMowerInitialOrientationException,
            IncorrectInitialCoordinatesException
    {
        int eastLimit = 10;
        int northLimit = 10;
        BorderPlateau plateau = new BorderPlateau(eastLimit, northLimit);
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(eastLimit, northLimit);
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1, 1, "N");
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand("MMM");
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
            IncorrectPlateauLimitsException,
            IncorrectCommandForMowerInitialOrientationException
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

    @Test
    void execute_should_not_deploy_an_mower_if_orientation_of_commands_for_initial_position_are_incorrect() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectPlateauLimitsException,
            IncorrectCommandForMowerInitialOrientationException
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

}