package org.david.cb.infrastructure;

import org.david.cb.application.deploy.CreatePlateauService;
import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.newmissionusecase.NewMissionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewMissionUseCaseTest {

    @Mock
    private DeployMowerService deployMowerService;

    @Mock
    private CreatePlateauService createPlateauService;

    @InjectMocks
    private NewMissionUseCase newMissionUseCase;


    @Test
    void execute_should_create_a_new_plateau() throws IncorrectCommandForPlateauLimitsException {

        newMissionUseCase.execute();

        Mockito.verify(createPlateauService).createPlateau();

    }
}