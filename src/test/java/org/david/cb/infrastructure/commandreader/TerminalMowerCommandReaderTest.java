package org.david.cb.infrastructure.commandreader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
class TerminalMowerCommandReaderTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    private TerminalMowerCommandReader terminalMowerCommandReader;

    @Test
    void readMowerInitialPositionCommands_should_return_read_from_scanner() {
        String expectedCommand = "ABC";
        Mockito.when(scanner.nextLine()).thenReturn(expectedCommand);

        String command = terminalMowerCommandReader.readMowerInitialPositionCommands();

        Assertions.assertEquals(command, expectedCommand);
    }

    @Test
    void readMowerMovementCommands_should_return_read_from_scanner() {
        String expectedCommand = "ABC";
        Mockito.when(scanner.nextLine()).thenReturn(expectedCommand);

        String command = terminalMowerCommandReader.readMowerMovementCommands();

        Assertions.assertEquals(command, expectedCommand);
    }
}