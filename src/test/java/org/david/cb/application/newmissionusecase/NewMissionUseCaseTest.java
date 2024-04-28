package org.david.cb.application.newmissionusecase;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.model.Coordinates;
import org.david.cb.model.commandreader.NewMissionCommandReader;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewMissionUseCaseTest {

    @Mock
    private DeployMowerService deployMowerService;

    @Mock
    private CreatePlateauService createPlateauService;

    @Mock
    private NewMissionCommandReader newMissionCommandReader;

    @Mock
    private PositionWriter positionWriter;

    @InjectMocks
    private NewMissionUseCase newMissionUseCase;


    @Test
    void execute_should_create_a_new_plateau()
            throws IncorrectCommandForPlateauLimitsException, IncorrectPlateauLimitsException {
        when(newMissionCommandReader.readExit()).thenReturn("exit");
        newMissionUseCase.execute();
        verify(createPlateauService).createPlateau();
    }

    @Test
    void execute_should_deploy_a_new_mower() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException,
            IncorrectPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(10, 10);
        Mower mower = new Mower(new Coordinates(5, 5), Orientation.NORTH, plateau);
        when(createPlateauService.createPlateau()).thenReturn(plateau);
        when(deployMowerService.deploy(plateau)).thenReturn(Optional.of(mower));
        when(newMissionCommandReader.readExit()).thenReturn("exit");
        newMissionUseCase.execute();
        verify(deployMowerService).deploy(plateau);
    }

    @Test
    void execute_should_print_mower_position() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException,
            IncorrectPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(10, 10);
        Mower mower = new Mower(new Coordinates(5, 5), Orientation.NORTH, plateau);
        when(createPlateauService.createPlateau()).thenReturn(plateau);
        when(deployMowerService.deploy(plateau)).thenReturn(Optional.of(mower));
        when(newMissionCommandReader.readExit()).thenReturn("exit");
        newMissionUseCase.execute();
        verify(positionWriter).write(
                mower.getCoordinates().getX() + " " +
                        mower.getCoordinates().getY() + " " +
                        mower.getOrientation().abbreviation
        );
    }

    @Test
    void execute_should_exit_loop_when_user_finish()
            throws IncorrectCommandForPlateauLimitsException, IncorrectPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(1, 1);
        when(createPlateauService.createPlateau()).thenReturn(plateau);
        when(newMissionCommandReader.readExit()).thenReturn("", "exit");
        newMissionUseCase.execute();
        verify(createPlateauService, times(1)).createPlateau();
        verify(deployMowerService, times(2)).deploy(plateau);
    }
}