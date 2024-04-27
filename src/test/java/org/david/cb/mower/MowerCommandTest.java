package org.david.cb.mower;

import org.david.cb.model.mower.MowerCommand;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MowerCommandTest {

    @Test
    void formChar_should_return_ROTATE_RIGHT_when_char_is_R() {

        Optional<MowerCommand> result = MowerCommand.fromChar('R');

        assertEquals(Optional.of(MowerCommand.ROTATE_RIGHT), result);
    }

    @Test
    void formChar_should_return_ROTATE_LEFT_when_char_is_L() {

        Optional<MowerCommand> result = MowerCommand.fromChar('L');

        assertEquals(Optional.of(MowerCommand.ROTATE_LEFT), result);
    }

    @Test
    void formChar_should_return_MOVE_FORWARD_when_char_is_M() {

        Optional<MowerCommand> result = MowerCommand.fromChar('M');

        assertEquals(Optional.of(MowerCommand.MOVE_FORWARD), result);
    }

    @Test
    void formChar_should_return_empty_when_char_is_an_invalid_command() {

        Optional<MowerCommand> result = MowerCommand.fromChar('/');

        assertEquals(Optional.empty(), result);
    }


}