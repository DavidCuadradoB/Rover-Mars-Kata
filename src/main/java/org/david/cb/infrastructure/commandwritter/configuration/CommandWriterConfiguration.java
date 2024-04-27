package org.david.cb.infrastructure.commandwritter.configuration;

import org.david.cb.commandwriter.PositionWriter;
import org.david.cb.infrastructure.commandwritter.TerminalPositionWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandWriterConfiguration {

    @Bean
    public PositionWriter commandWriter() {
        return new TerminalPositionWriter();
    }

}
