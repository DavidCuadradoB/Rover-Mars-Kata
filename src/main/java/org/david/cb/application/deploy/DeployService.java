package org.david.cb.application.deploy;

import org.david.cb.mower.IncorrectCommandException;

public interface DeployService {


    void deploy() throws IncorrectCommandException, IncorrectCommandForPlateauLimitsException, IncorrectCommandForMowerInitialPositionException;

}
