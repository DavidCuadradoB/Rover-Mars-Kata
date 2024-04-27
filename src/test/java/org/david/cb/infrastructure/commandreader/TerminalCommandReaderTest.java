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
class TerminalCommandReaderTest {


    @Mock
    Scanner scanner;

    @InjectMocks
    private TerminalCommandReader terminalCommandReader;


    @Test
    void readCommand_should_return_a_string_read_from_scanner() {
        String expectedCommand = "ABC";
        Mockito.when(scanner.nextLine()).thenReturn(expectedCommand);

        String command = terminalCommandReader.readCommand("a question");

        Assertions.assertEquals(command, expectedCommand);

    }
}