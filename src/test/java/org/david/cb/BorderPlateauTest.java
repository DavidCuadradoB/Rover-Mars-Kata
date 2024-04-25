package org.david.cb;

import org.junit.jupiter.api.Test;

import static org.david.cb.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Coordinates coordinates = new Coordinates(limitX-1, limitY-1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, NORTH);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() + 1);

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_north_but_is_in_the_border_should_no_move_to_north() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        Coordinates coordinates = new Coordinates(x, limitY);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, NORTH);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_east_should_move_to_east() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(limitX-1, limitY-1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, EAST);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX() + 1, coordinates.getY());

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_east_but_is_in_the_border_should_no_move_to_east() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int y = 4;
        Coordinates coordinates = new Coordinates(limitX, y);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, EAST);

        assertEquals(coordinates, newCoordinates);
    }


    @Test
    void calculatePosition_when_orientation_is_south_should_move_to_south() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(1, 1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, SOUTH);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() - 1);

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_south_but_is_in_the_border_should_no_move_to_south() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        Coordinates coordinates = new Coordinates(x, 0);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, SOUTH);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_west_should_move_to_west() {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(1, 1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, WEST);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX() - 1, coordinates.getY());

        assertEquals(expectedCoordinates, newCoordinates);
    }

}