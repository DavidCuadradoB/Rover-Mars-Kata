package org.david.cb.application.mower;

import org.david.cb.application.mower.command.CreateMowerCommand;
import org.david.cb.application.mower.command.MowerMovementCommand;
import org.david.cb.model.Coordinates;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeployMowerServiceTest {

    @InjectMocks
    private DeployMowerService deployMowerService;


    @Test
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader()
            throws IncorrectPlateauLimitsException {
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1, 2, "N");
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand("LMLMLMLMM");

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> optionalMower = deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau);

        Assertions.assertTrue(optionalMower.isPresent());
        Mower mower = optionalMower.get();
        Assertions.assertEquals(new Coordinates(1, 3), mower.getCoordinates());
        Assertions.assertEquals(Orientation.NORTH, mower.getOrientation());
    }

    @Test
    void deploy_should_throw_an_IncorrectCommandException_when_the_movement_has_incorrect_command()
            throws IncorrectPlateauLimitsException {
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1,2 , "N");
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand("MMR/RR");

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> mower = deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau);

        Assertions.assertEquals(mower, Optional.empty());

    }


    @Test
    void deploy_throw_an_IncorrectCommandForMowerInitialPositionException_when_the_Orientation_is_incorrect()
            throws IncorrectPlateauLimitsException {
        CreateMowerCommand createMowerCommand = new CreateMowerCommand(1 ,2, "J");
        MowerMovementCommand mowerMovementCommand = new MowerMovementCommand("MMRRR");

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> mower = deployMowerService.deploy(createMowerCommand, mowerMovementCommand, plateau);

        Assertions.assertEquals(mower, Optional.empty());
    }

}