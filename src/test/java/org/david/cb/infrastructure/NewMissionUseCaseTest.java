package org.david.cb.infrastructure;

import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.newmissionusecase.NewMissionUseCase;
import org.david.cb.model.Coordinates;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class NewMissionUseCaseTest {

    @Mock
    private DeployMowerService deployMowerService;

    @Mock
    private CreatePlateauService createPlateauService;

    @Mock
    private CommandReader commandReader;

    @Mock
    private PositionWriter positionWriter;

    @InjectMocks
    private NewMissionUseCase newMissionUseCase;


    @Test
    void execute_should_create_a_new_plateau() throws IncorrectCommandForPlateauLimitsException
    {
        Mockito.when(commandReader.readCommand("type 'exit' to exit the program or enter to add a new mower"))
                .thenReturn("exit");
        newMissionUseCase.execute();
        Mockito.verify(createPlateauService).createPlateau();
    }

    @Test
    void execute_should_deploy_a_new_mower() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException
    {
        BorderPlateau plateau = new BorderPlateau(10, 10);
        Mower mower = new Mower(new Coordinates(5, 5), Orientation.NORTH, plateau);
        Mockito.when(createPlateauService.createPlateau()).thenReturn(plateau);
        Mockito.when(deployMowerService.deploy(plateau)).thenReturn(Optional.of(mower));
        Mockito.when(commandReader.readCommand("type 'exit' to exit the program or enter to add a new mower"))
                .thenReturn("exit");
        newMissionUseCase.execute();
        Mockito.verify(deployMowerService).deploy(plateau);
    }

    @Test
    void execute_should_print_mower_position() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException
    {
        BorderPlateau plateau = new BorderPlateau(10, 10);
        Mower mower = new Mower(new Coordinates(5, 5), Orientation.NORTH, plateau);
        Mockito.when(createPlateauService.createPlateau()).thenReturn(plateau);
        Mockito.when(deployMowerService.deploy(plateau)).thenReturn(Optional.of(mower));
        Mockito.when(commandReader.readCommand("type 'exit' to exit the program or enter to add a new mower"))
                .thenReturn("exit");
        newMissionUseCase.execute();
        Mockito.verify(positionWriter).write(
                mower.getCoordinates().getX() + " " +
                        mower.getCoordinates().getY() + " " +
                        mower.getOrientation().abbreviation
        );
    }

    @Test
    void execute_should_exit_loop_when_user_finish() throws IncorrectCommandForPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(1, 1);
        Mockito.when(createPlateauService.createPlateau()).thenReturn(plateau);
        Mockito.when(commandReader.readCommand("type 'exit' to exit the program or enter to add a new mower"))
                .thenReturn("", "exit");
        newMissionUseCase.execute();
        Mockito.verify(createPlateauService, Mockito.times(1)).createPlateau();
        Mockito.verify(deployMowerService, Mockito.times(2)).deploy(plateau);
    }
}