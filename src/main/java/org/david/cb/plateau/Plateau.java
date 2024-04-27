package org.david.cb.plateau;

import org.david.cb.Coordinates;
import org.david.cb.mower.Orientation;

public interface Plateau {

    Coordinates calculatePosition(Coordinates currentCoordinates, Orientation orientation);

    void addObstacle(Coordinates coordinates);
}
