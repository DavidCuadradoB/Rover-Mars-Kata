package org.david.cb.deploy;

import org.david.cb.application.deploy.DeployMowerService;
import org.david.cb.model.Coordinates;
import org.david.cb.model.commandreader.CommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.mower.Mower;
import org.david.cb.model.mower.Orientation;
import org.david.cb.model.mower.exception.IncorrectInitialCoordinatesException;
import org.david.cb.model.plateau.BorderPlateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeployMowerServiceTest {

    @Mock
    private CommandReader commandReader;

    @Mock
    private PositionWriter positionWriter;

    @InjectMocks
    private DeployMowerService deployMowerService;


    @Test
    void deploy_should_create_a_plateau_with_the_values_get_from_command_reader() throws IncorrectInitialCoordinatesException {
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "LMLMLMLMM";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);
        Mockito.when(commandReader.readCommand("Introduce the mower's commands of movements"))
                .thenReturn(mowerMovement);

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> optionalMower = deployMowerService.deploy(plateau);

        Assertions.assertTrue(optionalMower.isPresent());
        Mower mower = optionalMower.get();
        Assertions.assertEquals(new Coordinates(1, 3), mower.getCoordinates());
        Assertions.assertEquals(Orientation.NORTH, mower.getOrientation());
    }

    @Test
    void deploy_should_throw_an_IncorrectCommandException_when_the_movement_has_incorrect_command() {
        String mowerInitialPosition = "1 2 N";
        String mowerMovement = "MMR/RR";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);
        Mockito.when(commandReader.readCommand("Introduce the mower's commands of movements"))
                .thenReturn(mowerMovement);

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> mower = deployMowerService.deploy(plateau);

        Assertions.assertEquals(mower, Optional.empty());


    }

    @Test
    void deploy_should_throw_an_IncorrectCommandForMowerInitialPositionException_when_the_mower_initial_position_has_incorrect_command() {
        String mowerInitialPosition = "a a N";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> mower = deployMowerService.deploy(plateau);

        Assertions.assertEquals(mower, Optional.empty());
    }

    @Test
    void deploy_throw_an_IncorrectCommandForMowerInitialPositionException_when_the_Orientation_is_incorrect() {
        String mowerInitialPosition = "1 4 J";

        Mockito.when(commandReader.readCommand("Introduce the mower initial values"))
                .thenReturn(mowerInitialPosition);

        BorderPlateau plateau = new BorderPlateau(5, 5);
        Optional<Mower> mower = deployMowerService.deploy(plateau);

        Assertions.assertEquals(mower, Optional.empty());
    }

}