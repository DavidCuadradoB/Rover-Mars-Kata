package org.david.cb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoverTest {

    @Test
    void create_rover_should_work() {
        Coordinates coordinates = new Coordinates(0, 0);
        Rover rover = new Rover(coordinates);
        Assertions.assertNotNull(rover);
        Assertions.assertEquals(coordinates, rover.getCoordinates());
    }

    @Test
    void move_north_the_rover_should_move_Forward_north() {
        int y = 0;
        int x = 0;
        Rover rover = new Rover(new Coordinates(x, y));
        Rover movedRover = rover.moveForward();
        Coordinates coordinates = movedRover.getCoordinates();
        Assertions.assertEquals(coordinates.getX(), x);
        Assertions.assertEquals(coordinates.getY(), y + 1);
    }

}