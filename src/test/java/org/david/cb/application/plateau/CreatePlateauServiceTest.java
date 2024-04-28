package org.david.cb.application.plateau;

import org.david.cb.application.plateau.command.CreatePlateauCommand;
import org.david.cb.model.plateau.BorderPlateau;
import org.david.cb.model.plateau.Plateau;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePlateauServiceTest {

    @InjectMocks
    private CreatePlateauService createPlateauService;

    @Test
    void createPlateau_should_create_a_plateau_with_the_values_get_from_command_reader()
            throws IncorrectPlateauLimitsException {
        CreatePlateauCommand createPlateauCommand = new CreatePlateauCommand(5, 5);

        Plateau plateau = createPlateauService.createPlateau(createPlateauCommand);
        Plateau expectedPlateau = new BorderPlateau(5, 5);

        Assertions.assertEquals(plateau, expectedPlateau);
    }

}