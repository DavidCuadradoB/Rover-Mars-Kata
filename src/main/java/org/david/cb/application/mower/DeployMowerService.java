package org.david.cb.application.mower;

import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.infrastructure.controller.exception.IncorrectCommandForMowerInitialOrientationException;
import org.david.cb.model.Coordinates;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.Plateau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DeployMowerService {

    private final Logger logger = LoggerFactory.getLogger(DeployMowerService.class);

    public Optional<Mower> deploy(
            CreateMowerCommand createMowerCommand,
            MowerMovementCommand mowerMovementCommand,
            Plateau plateau) {
        try {
            Mower mower = createMower(createMowerCommand, plateau);
            return Optional.of(mower.execute(mowerMovementCommand.movements()));
        } catch (IncorrectCommandForMowerInitialOrientationException | IncorrectInitialCoordinatesException exception) {
            logger.error("Impossible to deploy the mower into the plateau", exception);
            return Optional.empty();
        }
    }

    private Mower createMower(CreateMowerCommand createMowerCommand, Plateau plateau)
            throws IncorrectCommandForMowerInitialOrientationException, IncorrectInitialCoordinatesException {

        Coordinates initialCoordinates = new Coordinates(
                createMowerCommand.x(),
                createMowerCommand.y()
        );

        return new Mower(initialCoordinates, createMowerCommand.orientation(), plateau);
    }

}
