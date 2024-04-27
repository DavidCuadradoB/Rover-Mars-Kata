package org.david.cb.infrastructure.commandwritter.configuration;

import org.david.cb.commandwriter.CommandWriter;
import org.david.cb.infrastructure.commandwritter.TerminalCommandWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandWriterConfiguration {

    @Bean
    public CommandWriter commandWriter() {
        return new TerminalCommandWriter();
    }

}
