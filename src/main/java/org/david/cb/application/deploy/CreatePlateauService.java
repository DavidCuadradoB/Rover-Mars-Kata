package org.david.cb.application.deploy;

import org.david.cb.application.deploy.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.Plateau;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatePlateauService {

    private final CommandReader commandReader;

    public CreatePlateauService(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    public Plateau createPlateau() throws IncorrectCommandForPlateauLimitsException {
        String plateauLimitsRaw = commandReader.readCommand();
        String regexPlateauLimits = "(\\d) (\\d)";

        Pattern patternPlateauLimits = Pattern.compile(regexPlateauLimits);
        Matcher matcherPlateauLimits = patternPlateauLimits.matcher(plateauLimitsRaw);

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
