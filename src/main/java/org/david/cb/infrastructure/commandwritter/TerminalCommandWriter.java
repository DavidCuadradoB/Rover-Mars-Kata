package org.david.cb.infrastructure.commandwritter;

import org.david.cb.commandwriter.CommandWriter;

public class TerminalCommandWriter implements CommandWriter {

    @Override
    public void write(String position) {
        System.out.println(position);
    }
}
