package org.david.cb.application.deploy;

import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;

public interface DeployService {


    void deploy() throws IncorrectCommandException, IncorrectCommandForPlateauLimitsException, IncorrectCommandForMowerInitialPositionException;

}
