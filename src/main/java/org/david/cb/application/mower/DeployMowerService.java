package org.david.cb.application.mower;

import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.application.mower.exceptions.IncorrectCommandException;
import org.david.cb.application.mower.exceptions.IncorrectCommandForMowerInitialOrientationException;
import org.david.cb.model.Coordinates;
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

public class DeployMowerService {

    private final Logger logger = LoggerFactory.getLogger(DeployMowerService.class);

    public Optional<Mower> deploy(
            CreateMowerCommand createMowerCommand,
            MowerMovementCommand mowerMovementCommand,
            Plateau plateau) {
        try {
            Mower mower = getMower(createMowerCommand, plateau);
            List<MowerCommand> mowerCommands = getMowerCommandFromString(mowerMovementCommand);
            return Optional.of(mower.execute(mowerCommands));
        } catch (IncorrectCommandForMowerInitialOrientationException | IncorrectInitialCoordinatesException exception) {
            logger.error("Impossible to deploy the mower into the plateau", exception);
            return Optional.empty();
        } catch (IncorrectCommandException exception) {
            logger.error("Impossible to move the mower", exception);
            return Optional.empty();
        }
    }

    private Mower getMower(CreateMowerCommand createMowerCommand, Plateau plateau)
            throws IncorrectCommandForMowerInitialOrientationException, IncorrectInitialCoordinatesException {

        Coordinates initialCoordinates = new Coordinates(
                createMowerCommand.x(),
                createMowerCommand.y()
        );

        Orientation orientation = Orientation.forAbbreviation(createMowerCommand.orientation())
                .orElseThrow(() ->
                        new IncorrectCommandForMowerInitialOrientationException(createMowerCommand.orientation())
                );

        return new Mower(initialCoordinates, orientation, plateau);
    }

    private List<MowerCommand> getMowerCommandFromString(MowerMovementCommand mowerMovementCommand)
            throws IncorrectCommandException {
        List<MowerCommand> mowerCommands = new ArrayList<>();
        for (char c : mowerMovementCommand.movements().toCharArray()) {
            mowerCommands.add(MowerCommand.fromChar(c).orElseThrow(() -> new IncorrectCommandException(c)));
        }
        return mowerCommands;
    }
}
