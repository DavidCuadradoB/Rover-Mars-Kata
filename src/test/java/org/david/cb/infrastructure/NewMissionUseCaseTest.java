package org.david.cb.infrastructure;

import org.david.cb.application.deploy.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.newmissionusecase.NewMissionUseCase;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NewMissionUseCaseTest {

    @Mock
    private DeployMowerService deployMowerService;

    @Mock
    private CreatePlateauService createPlateauService;

    @Mock
    private CommandReader commandReader;

    @InjectMocks
    private NewMissionUseCase newMissionUseCase;


    @Test
    void execute_should_create_a_new_plateau() throws
            IncorrectCommandForPlateauLimitsException,
            IncorrectInitialCoordinatesException,
            IncorrectCommandForMowerInitialPositionException,
            IncorrectCommandException
    {
        Mockito.when(commandReader.readCommand()).thenReturn("exit");
        newMissionUseCase.execute();
        Mockito.verify(createPlateauService).createPlateau();
    }

    @Test
    void execute_should_deploy_a_new_mower() throws
            IncorrectInitialCoordinatesException,
            IncorrectCommandForMowerInitialPositionException,
            IncorrectCommandException,
            IncorrectCommandForPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(1, 1);
        Mockito.when(createPlateauService.createPlateau()) .thenReturn(plateau);
        Mockito.when(commandReader.readCommand()).thenReturn("exit");
        newMissionUseCase.execute();
        Mockito.verify(deployMowerService).deploy(plateau);
    }

    @Test
    void execute_should_exit_loop_when_user_finish() throws
            IncorrectInitialCoordinatesException,
            IncorrectCommandForMowerInitialPositionException,
            IncorrectCommandException,
            IncorrectCommandForPlateauLimitsException
    {
        BorderPlateau plateau = new BorderPlateau(1, 1);
        Mockito.when(createPlateauService.createPlateau()).thenReturn(plateau);
        Mockito.when(commandReader.readCommand()).thenReturn("exit");
        newMissionUseCase.execute();
        Mockito.verify(createPlateauService, Mockito.times(1)).createPlateau();
        Mockito.verify(deployMowerService, Mockito.times(1)).deploy(plateau);
    }
}