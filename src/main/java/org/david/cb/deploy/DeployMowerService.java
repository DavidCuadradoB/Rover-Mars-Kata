package org.david.cb.deploy;

import org.david.cb.commandreader.CommandReader;

public class DeployMowerService implements DeployService {

    private final CommandReader commandReader;

    public DeployMowerService(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    @Override
    public void deploy() {

    }
}
