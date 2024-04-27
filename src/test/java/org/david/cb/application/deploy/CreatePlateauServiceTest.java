package org.david.cb.application.deploy;

import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreatePlateauServiceTest {

    @Mock
    private CommandReader commandReader;

    @InjectMocks
    private CreatePlateauService createPlateauService;

    @Test
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader()
            throws IncorrectCommandForPlateauLimitsException {
        String plateauSize = "5 5";

        Mockito.when(commandReader.readCommand()).thenReturn(plateauSize);

        Plateau plateau = createPlateauService.createPlateau();
        Plateau expectedPlateau = new BorderPlateau(5, 5);

        Assertions.assertEquals(plateau, expectedPlateau);
    }

    @Test
    void deploy_should_throw_an_IncorrectCommandForPlateauLimitsException_when_the_plateauSize_has_incorrect_command() {
        String plateauSize = "a a";
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "MMRRR";

        Mockito.when(commandReader.readCommand()).thenReturn(plateauSize, mowerInitialPosition, mowerMovement);

        IncorrectCommandForPlateauLimitsException exception = assertThrows(
                IncorrectCommandForPlateauLimitsException.class, () -> createPlateauService.createPlateau()
        );

        String expectedMessage = "The command: a a is not a valid command for plateau limits.";

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }


}