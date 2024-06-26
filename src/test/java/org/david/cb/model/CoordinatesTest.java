package org.david.cb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinatesTest {

    @Test
    void create_coordinates_correctly() {
        int x = 2;
        int y = 4;
        Coordinates coordinates = new Coordinates(x, y);

        Assertions.assertEquals(x, coordinates.getX());
        Assertions.assertEquals(y, coordinates.getY());
    }

}