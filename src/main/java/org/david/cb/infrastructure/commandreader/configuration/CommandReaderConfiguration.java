package org.david.cb.infrastructure.commandreader.configuration;

import org.david.cb.commandreader.CommandReader;
import org.david.cb.infrastructure.commandreader.TerminalCommandReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class CommandReaderConfiguration {

    @Bean
    public CommandReader commandReader(Scanner scanner) {
        return new TerminalCommandReader(scanner);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
