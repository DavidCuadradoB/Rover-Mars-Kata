package org.david.cb.deploy;

import org.david.cb.Coordinates;
import org.david.cb.MoveCommand;
import org.david.cb.commandreader.CommandReader;
import org.david.cb.commandwriter.PositionWriter;
import org.david.cb.mower.IncorrectCommandException;
import org.david.cb.mower.Mower;
import org.david.cb.mower.Orientation;
import org.david.cb.plateau.BorderPlateau;
import org.david.cb.plateau.Plateau;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeployMowerService implements DeployService {

    private final CommandReader commandReader;
    private final PositionWriter positionWriter;

    public DeployMowerService(CommandReader commandReader, PositionWriter positionWriter) {
        this.commandReader = commandReader;
        this.positionWriter = positionWriter;
    }

    @Override
    public void deploy() throws IncorrectCommandException {

        Plateau plateau = getPlateau();

        Mower mower = getMower(plateau);

        String mowerMovement = commandReader.readCommand();
        Mower movedMower = mower.execute(new MoveCommand(mowerMovement));

        positionWriter.write(
                movedMower.getCoordinates().getX() + " " +
                        movedMower.getCoordinates().getY() + " " +
                        movedMower.getOrientation().abbreviation
        );

    }

    private Mower getMower(Plateau plateau) {
        String mowerInitialPosition = commandReader.readCommand();
        String regexMowerInitialPosition = "(\\d) (\\d) (\\w)";

        Pattern patternMowerInitialPosition = Pattern.compile(regexMowerInitialPosition);
        Matcher matcherMowerInitialPosition = patternMowerInitialPosition.matcher(mowerInitialPosition);

        if (matcherMowerInitialPosition.find()) {
            Coordinates initialCoordinates = new Coordinates(
                    Integer.parseInt(matcherMowerInitialPosition.group(1)),
                    Integer.parseInt(matcherMowerInitialPosition.group(2))
            );

            return new Mower(initialCoordinates, Orientation.NORTH, plateau);
        }
        return null;
    }

    private Plateau getPlateau() {
        String plateauLimitsRaw = commandReader.readCommand();
        String regexPlateauLimits = "(\\d) (\\d)";

        Pattern patternPlateauLimits = Pattern.compile(regexPlateauLimits);
        Matcher matcherPlateauLimits = patternPlateauLimits.matcher(plateauLimitsRaw);

        if (matcherPlateauLimits.find()) {
            return new BorderPlateau(
                    Integer.parseInt(matcherPlateauLimits.group(1)),
                    Integer.parseInt(matcherPlateauLimits.group(2))
            );
        }
        return null;
    }
}
