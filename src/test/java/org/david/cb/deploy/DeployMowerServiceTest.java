package org.david.cb.deploy;

import org.david.cb.commandreader.CommandReader;
import org.david.cb.commandwriter.PositionWriter;
import org.david.cb.mower.IncorrectCommandException;
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
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader() throws IncorrectCommandException {
        String plateauSize = "5 5";
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "LMLMLMLMM";
        String expectedPosition = "1 3 N";

        Mockito.when(commandReader.readCommand()).thenReturn(plateauSize, mowerInitialPosition, mowerMovement);

        deployMowerService.deploy();

        Mockito.verify(positionWriter).write(expectedPosition);

    }

    @Test
    void deploy_should_throw_an_IncorrectCommandException_when_the_movement_has_incorrect_command()
            throws IncorrectCommandException {
        String plateauSize = "5 5";
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "MMR/RR";

        Mockito.when(commandReader.readCommand()).thenReturn(plateauSize, mowerInitialPosition, mowerMovement);

        IncorrectCommandException exception = assertThrows(
                IncorrectCommandException.class, () -> deployMowerService.deploy()
        );

        String expectedMessage = "The command : / is not a valid character.";

        Assertions.assertEquals(expectedMessage, exception.getMessage());

    }



}