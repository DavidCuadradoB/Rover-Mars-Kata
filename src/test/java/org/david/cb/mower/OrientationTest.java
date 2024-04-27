package org.david.cb.mower;

import org.david.cb.model.mower.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class OrientationTest {

    @Test
    void formChar_should_return_NORTH_when_abbreviation_is_N() {
        Optional<Orientation> orientation = Orientation.forAbbreviation("N");

        Assertions.assertEquals(Optional.of(Orientation.NORTH), orientation);
    }

    @Test
    void formChar_should_return_EAST_when_abbreviation_is_E() {
        Optional<Orientation> orientation = Orientation.forAbbreviation("E");

        Assertions.assertEquals(Optional.of(Orientation.EAST), orientation);
    }

    @Test
    void formChar_should_return_SOUTH_when_abbreviation_is_S() {
        Optional<Orientation> orientation = Orientation.forAbbreviation("S");

        Assertions.assertEquals(Optional.of(Orientation.SOUTH), orientation);
    }

    @Test
    void formChar_should_return_WEST_when_abbreviation_is_W() {
        Optional<Orientation> orientation = Orientation.forAbbreviation("W");

        Assertions.assertEquals(Optional.of(Orientation.WEST), orientation);
    }

    @Test
    void formChar_should_return_empty_when_abbreviation_is_invalid() {
        Optional<Orientation> orientation = Orientation.forAbbreviation("N");

        Assertions.assertEquals(Optional.of(Orientation.NORTH), orientation);
    }
}