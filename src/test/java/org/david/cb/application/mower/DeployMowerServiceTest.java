package org.david.cb.application.mower;

import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.model.Coordinates;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.MowerCommand;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DeployMowerServiceTest {

    @InjectMocks
    private DeployMowerService deployMowerService;


    @Test
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader()
            throws IncorrectPlateauLimitsException {
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1, 2, Orientation.NORTH);
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.ROTATE_LEFT,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.ROTATE_LEFT,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.ROTATE_LEFT,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.ROTATE_LEFT,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD
                )
        );

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> optionalMower = deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau);

        Assertions.assertTrue(optionalMower.isPresent());
        Mower mower = optionalMower.get();
        Assertions.assertEquals(new Coordinates(1, 3), mower.getCoordinates());
        Assertions.assertEquals(Orientation.NORTH, mower.getOrientation());
    }


    @Test
    void deploy_should_return_optional_empty_when_the_mower_cannot_be_created() {
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1 ,2, Orientation.NORTH);

        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand(
                Arrays.asList(
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.MOVE_FORWARD,
                        MowerCommand.ROTATE_RIGHT,
                        MowerCommand.ROTATE_RIGHT,
                        MowerCommand.ROTATE_RIGHT
                )
        );

        BorderPlateau plateau = Mockito.mock(BorderPlateau.class);
        Mockito.when(plateau.checkCoordinates(any())).thenReturn(false);
        deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau);
        Optional<Mower> mowerInObstacle = deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau);

        Assertions.assertEquals(mowerInObstacle, Optional.empty());
    }


}