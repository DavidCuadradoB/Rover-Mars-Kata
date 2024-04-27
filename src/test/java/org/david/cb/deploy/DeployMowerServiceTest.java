package org.david.cb.deploy;

import org.david.cb.commandreader.CommandReader;
import org.david.cb.commandwriter.PositionWriter;
import org.david.cb.mower.IncorrectCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
}