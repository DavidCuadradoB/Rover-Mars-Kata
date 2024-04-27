package org.david.cb.infrastructure.commandwritter;

import org.david.cb.commandwriter.PositionWriter;

public class TerminalPositionWriter implements PositionWriter {

    @Override
    public void write(String position) {
        System.out.println(position);
    }
}
