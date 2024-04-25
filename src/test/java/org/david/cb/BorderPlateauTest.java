package org.david.cb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.david.cb.Orientation.*;
import static org.junit.jupiter.api.Assertions.*;

class BorderPlateauTest {

    @Test
    void create_borderPlateau_should_determinate_the_limits() {
        int limitX = 5;
        int limitY = 10;

        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        assertEquals(limitX, borderPlateau.getLimitX());
        assertEquals(limitY, borderPlateau.getLimitY());
    }

    @Test
    void calculatePosition_when_orientation_is_north_should_move_to_north() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 4;
        Coordinates coordinates = new Coordinates(x, y);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, NORTH);
        Coordinates expectedCoordinates = new Coordinates(x, y + 1);

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_east_should_move_to_east() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 4;
        Coordinates coordinates = new Coordinates(x, y);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, EAST);
        Coordinates expectedCoordinates = new Coordinates(x + 1, y);

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_south_should_move_to_south() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 4;
        Coordinates coordinates = new Coordinates(x, y);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, SOUTH );
        Coordinates expectedCoordinates = new Coordinates(x, y - 1);

        assertEquals(expectedCoordinates, newCoordinates);
    }

}