package org.david.cb.application.mower;

import org.david.cb.application.mower.exceptions.IncorrectCommandException;
import org.david.cb.application.mower.exceptions.IncorrectCommandForMowerInitialPositionException;
import org.david.cb.model.Coordinates;
import org.david.cb.model.commandreader.MowerCommandReader;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.MowerCommand;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.Plateau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeployMowerService  {

    public static final String REGEX_MOWER_INITIAL_POSITION = "(\\d) (\\d) (\\w)";
    private final MowerCommandReader mowerCommandReader;
    Logger logger = LoggerFactory.getLogger(DeployMowerService.class);

    public DeployMowerService(MowerCommandReader mowerCommandReader) {
        this.mowerCommandReader = mowerCommandReader;
    }

    public Optional<Mower> deploy(Plateau plateau) {

        try {
            Mower mower = getMower(plateau);
            List<MowerCommand> mowerCommands = new ArrayList<>();
            getMowerCommandFromString(mowerCommands);
            Mower movedMower = mower.execute(mowerCommands);

            return Optional.of(movedMower);

        } catch (IncorrectCommandForMowerInitialPositionException | IncorrectInitialCoordinatesException exception) {
            logger.error("Impossible to deploy the mower into the plateau", exception);
            return Optional.empty();
        } catch (IncorrectCommandException exception) {
            logger.error("Impossible to move the mower", exception);
            return Optional.empty();
        }
    }

    private Mower getMower(Plateau plateau) throws IncorrectCommandForMowerInitialPositionException, IncorrectInitialCoordinatesException {
        String mowerInitialPosition = mowerCommandReader.readMowerInitialPositionCommands();
        Matcher matcherMowerInitialPosition = Pattern.compile(REGEX_MOWER_INITIAL_POSITION).matcher(mowerInitialPosition);

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

    private void getMowerCommandFromString(List<MowerCommand> mowerCommands) throws IncorrectCommandException {
        for (char c : mowerCommandReader.readMowerMovementCommands().toCharArray()) {
            mowerCommands.add(MowerCommand.fromChar(c).orElseThrow(() -> new IncorrectCommandException(c)));
        }
    }
}
