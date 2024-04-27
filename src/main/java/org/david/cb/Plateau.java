package org.david.cb;

public interface Plateau {

    Coordinates calculatePosition(Coordinates currentCoordinates, Orientation orientation);

    void addObstacle(Coordinates coordinates);
}
