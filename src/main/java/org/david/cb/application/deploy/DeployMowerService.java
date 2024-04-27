package org.david.cb.application.deploy;

import org.david.cb.application.deploy.exceptions.IncorrectCommandException;
import org.david.cb.application.deploy.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.model.Coordinates;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.MowerCommand;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.Plateau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeployMowerService  {

    private final CommandReader commandReader;
    private final PositionWriter positionWriter;
    Logger logger = LoggerFactory.getLogger(DeployMowerService.class);

    public DeployMowerService(CommandReader commandReader, PositionWriter positionWriter) {
        this.commandReader = commandReader;
        this.positionWriter = positionWriter;
    }

    public void deploy(Plateau plateau) {

        try {
            Mower mower = getMower(plateau);
            List<MowerCommand> mowerCommands = new ArrayList<>();
            getMowerCommandFromString(mowerCommands);
            Mower movedMower = mower.execute(mowerCommands);

            positionWriter.write(
                    movedMower.getCoordinates().getX() + " " +
                            movedMower.getCoordinates().getY() + " " +
                            movedMower.getOrientation().abbreviation
            );
        } catch (IncorrectCommandForMowerInitialPositionException | IncorrectInitialCoordinatesException exception) {
            logger.error("Impossible to deploy the mower into the plateau", exception);
        } catch (IncorrectCommandException exception) {
            logger.error("Impossible to move the mower", exception);
        }

    }

    private void getMowerCommandFromString(List<MowerCommand> mowerCommands) throws IncorrectCommandException {
        for (char c : commandReader.readCommand("Introduce the mower's commands of movements").toCharArray()) {
            mowerCommands.add(MowerCommand.fromChar(c).orElseThrow(() -> new IncorrectCommandException(c)));
        }
    }

    private Mower getMower(Plateau plateau) throws IncorrectCommandForMowerInitialPositionException, IncorrectInitialCoordinatesException {
        String mowerInitialPosition = commandReader.readCommand("Introduce the mower initial values");
        String regexMowerInitialPosition = "(\\d) (\\d) (\\w)";

        Pattern patternMowerInitialPosition = Pattern.compile(regexMowerInitialPosition);
        Matcher matcherMowerInitialPosition = patternMowerInitialPosition.matcher(mowerInitialPosition);

        if (matcherMowerInitialPosition.find()) {
            Coordinates initialCoordinates = new Coordinates(
                    Integer.parseInt(matcherMowerInitialPosition.group(1)),
                    Integer.parseInt(matcherMowerInitialPosition.group(2))
            );

            Orientation orientation = Orientation.forAbbreviation(matcherMowerInitialPosition.group(3))
                    .orElseThrow(() -> new IncorrectCommandForMowerInitialPositionException(mowerInitialPosition));

            return new Mower(initialCoordinates, orientation, plateau);
        }
        throw new IncorrectCommandForMowerInitialPositionException(mowerInitialPosition);
    }
}
