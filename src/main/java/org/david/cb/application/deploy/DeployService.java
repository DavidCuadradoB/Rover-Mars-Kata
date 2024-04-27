package org.david.cb.application.deploy;

import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.mower.exception.IncorrectInitialPositionException;

public interface DeployService {


    void deploy() throws IncorrectCommandException, IncorrectCommandForPlateauLimitsException, IncorrectCommandForMowerInitialPositionException, IncorrectInitialPositionException;

}
