package org.david.cb.deploy;

import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DeployMowerServiceTest {

    @Mock
    private CommandReader commandReader;

    @Mock
    private PositionWriter positionWriter;

    @InjectMocks
    private DeployMowerService deployMowerService;


    @Test
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader() throws
            IncorrectCommandException,
            IncorrectCommandForMowerInitialPositionException,
            IncorrectInitialCoordinatesException {
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "LMLMLMLMM";
        String expectedPosition = "1 3 N";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);
        Mockito.when(commandReader.readCommand("Introduce the mower's commands of movements"))
                .thenReturn(mowerMovement);

        deployMowerService.deploy(new BorderPlateau(5, 5));

        Mockito.verify(positionWriter).write(expectedPosition);

    }

    @Test
    void deploy_should_throw_an_IncorrectCommandException_when_the_movement_has_incorrect_command() {
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "MMR/RR";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);
        Mockito.when(commandReader.readCommand("Introduce the mower's commands of movements"))
                .thenReturn(mowerMovement);

        IncorrectCommandException exception = assertThrows(
                IncorrectCommandException.class, () -> deployMowerService.deploy(new BorderPlateau(5, 5))
        );

        String expectedMessage = "The command : / is not a valid character.";

        Assertions.assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void deploy_should_throw_an_IncorrectCommandForMowerInitialPositionException_when_the_mower_initial_position_has_incorrect_command() {
        String mowerInitialPosition = "a a N";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);

        IncorrectCommandForMowerInitialPositionException exception = assertThrows(
                IncorrectCommandForMowerInitialPositionException.class, () -> deployMowerService.deploy(new BorderPlateau(5, 5))
        );

        String expectedMessage = "The command: a a N is not a valid command for mower initial position.";

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void deploy_throw_an_IncorrectCommandForMowerInitialPositionException_when_the_Orientation_is_incorrect() {
        String mowerInitialPosition = "1 4 J";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);

        IncorrectCommandForMowerInitialPositionException exception = assertThrows(
                IncorrectCommandForMowerInitialPositionException.class, () -> deployMowerService.deploy(new BorderPlateau(5, 5))
        );

        String expectedMessage = "The command: 1 4 J is not a valid command for mower initial position.";

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}