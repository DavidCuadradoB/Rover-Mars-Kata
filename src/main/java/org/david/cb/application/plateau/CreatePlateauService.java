package org.david.cb.application.plateau;

import org.david.cb.application.plateau.command.CreatePlateauCommand;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.Plateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;

public class CreatePlateauService {

    public Plateau createPlateau(CreatePlateauCommand createPlateauCommand) throws IncorrectPlateauLimitsException {
        return new BorderPlateau(createPlateauCommand.limitX(), createPlateauCommand.limitY());
    }
}
