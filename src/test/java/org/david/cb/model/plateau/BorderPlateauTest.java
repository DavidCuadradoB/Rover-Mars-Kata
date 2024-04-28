package org.david.cb.model.plateau;

import org.david.cb.model.Coordinates;
import org.david.cb.model.plateau.exception.IncorrectPlateauLimitsException;
import org.junit.jupiter.api.Test;

import static org.david.cb.model.mower.Orientation.*;
import static org.junit.jupiter.api.Assertions.*;

class BorderPlateauTest {


    @Test
    void create_the_limitX_should_be_higher_than_0() {

        int limitX = 0;
        int limitY = 10;

        IncorrectPlateauLimitsException exception = assertThrows(
                IncorrectPlateauLimitsException.class, () -> new BorderPlateau(limitX, limitY)
        );

        assertEquals(String.format("The plateau limits: %s, %s are incorrect.", limitX, limitY), exception.getMessage());

    }

    @Test
    void create_the_limitY_should_be_higher_than_0() {

        int limitX = 10;
        int limitY = 0;

        IncorrectPlateauLimitsException exception = assertThrows(
                IncorrectPlateauLimitsException.class, () -> new BorderPlateau(limitX, limitY)
        );

        assertEquals(String.format("The plateau limits: %s, %s are incorrect.", limitX, limitY), exception.getMessage());

    }

    @Test
    void calculatePosition_when_orientation_is_north_should_move_to_north() throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(limitX-1, limitY-1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, NORTH);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() + 1);

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_north_but_is_in_the_border_should_no_move_to_north()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        Coordinates coordinates = new Coordinates(x, limitY);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, NORTH);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_north_but_there_are_an_obstacle_should_no_move_to_north()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 3;
        Coordinates coordinates = new Coordinates(x, y);
        borderPlateau.addObstacle(new Coordinates(x, y+1));

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, NORTH);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_east_should_move_to_east() throws IncorrectPlateauLimitsException  {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(limitX-1, limitY-1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, EAST);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX() + 1, coordinates.getY());

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_east_but_is_in_the_border_should_no_move_to_east()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int y = 4;
        Coordinates coordinates = new Coordinates(limitX, y);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, EAST);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_east_but_there_are_an_obstacle_should_no_move_to_east()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 3;
        Coordinates coordinates = new Coordinates(x, y);
        borderPlateau.addObstacle(new Coordinates(x+1, y));

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, EAST);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_south_should_move_to_south() throws IncorrectPlateauLimitsException  {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(1, 1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, SOUTH);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() - 1);

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_south_but_is_in_the_border_should_no_move_to_south()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        Coordinates coordinates = new Coordinates(x, 0);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, SOUTH);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_south_but_there_are_an_obstacle_should_no_move_to_south()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 3;
        Coordinates coordinates = new Coordinates(x, y);
        borderPlateau.addObstacle(new Coordinates(x, y - 1));

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, SOUTH);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_west_should_move_to_west() throws IncorrectPlateauLimitsException  {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(1, 1);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, WEST);
        Coordinates expectedCoordinates = new Coordinates(coordinates.getX() - 1, coordinates.getY());

        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_west_but_is_in_the_border_should_no_move_to_west()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int y = 4;
        Coordinates coordinates = new Coordinates(0, y);

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, WEST);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void calculatePosition_when_orientation_is_west_but_there_are_an_obstacle_should_no_move_to_west()
            throws IncorrectPlateauLimitsException {

        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        int x = 2;
        int y = 3;
        Coordinates coordinates = new Coordinates(x, y);
        borderPlateau.addObstacle(new Coordinates(x - 1, y));

        Coordinates newCoordinates = borderPlateau.calculatePosition(coordinates, WEST);

        assertEquals(coordinates, newCoordinates);
    }

    @Test
    void checkCoordinates_should_return_true_if_coordinates_are_inside_plateau()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(limitX, limitY);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertTrue(checkedCoordinates);
    }

    @Test
    void checkCoordinates_should_return_true_if_coordinates_are_in_0_0_position()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(0, 0);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertTrue(checkedCoordinates);
    }

    @Test
    void checkCoordinates_should_return_false_if_coordinates_are_outside_east_of_plateau()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(limitX+1, limitY);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertFalse(checkedCoordinates);
    }

    @Test
    void checkCoordinates_should_return_false_if_coordinates_are_outside_north_of_plateau()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(limitX, limitY+1);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertFalse(checkedCoordinates);
    }

    @Test
    void checkCoordinates_should_return_false_if_coordinates_are_outside_south_of_plateau()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(0, -1);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertFalse(checkedCoordinates);
    }

    @Test
    void checkCoordinates_should_return_false_if_coordinates_are_outside_west_of_plateau()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;
        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);

        Coordinates coordinates = new Coordinates(-1, 0);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertFalse(checkedCoordinates);
    }

    @Test
    void checkCoordinates_should_return_false_if_coordinates_are_in_an_obstacle()
            throws IncorrectPlateauLimitsException {
        int limitX = 5;
        int limitY = 10;

        Coordinates coordinates = new Coordinates(2, 3);

        BorderPlateau borderPlateau = new BorderPlateau(limitX, limitY);
        borderPlateau.addObstacle(coordinates);

        boolean checkedCoordinates = borderPlateau.checkCoordinates(coordinates);

        assertFalse(checkedCoordinates);
    }
}