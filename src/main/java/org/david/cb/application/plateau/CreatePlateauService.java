package org.david.cb.application.plateau;

import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.model.commandreader.PlateauCommandReader;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.Plateau;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatePlateauService {

    public static final String REGEX_PLATEAU_LIMITS = "(\\d) (\\d)";
    private final PlateauCommandReader plateauCommandReader;

    public CreatePlateauService(PlateauCommandReader plateauCommandReader) {
        this.plateauCommandReader = plateauCommandReader;
    }

    public Plateau createPlateau() throws IncorrectCommandForPlateauLimitsException {
        String plateauLimitsRaw = plateauCommandReader.readPlateauLimits();
        Matcher matcherPlateauLimits = Pattern.compile(REGEX_PLATEAU_LIMITS).matcher(plateauLimitsRaw);

        if (matcherPlateauLimits.find()) {
            return new BorderPlateau(
                    Integer.parseInt(matcherPlateauLimits.group(1)),
                    Integer.parseInt(matcherPlateauLimits.group(2))
            );
        } else {
            throw new IncorrectCommandForPlateauLimitsException(plateauLimitsRaw);
        }
    }
}
