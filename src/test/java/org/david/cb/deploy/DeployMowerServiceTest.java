package org.david.cb.deploy;

import org.david.cb.commandreader.CommandReader;
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

    @InjectMocks
    private DeployMowerService deployMowerService;


    @Test
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader() {

        Mockito.when(commandReader.readCommand()).thenReturn("5 5", "1 2 N", "LMLMLMLMM");
        deployMowerService.deploy();

    }
}