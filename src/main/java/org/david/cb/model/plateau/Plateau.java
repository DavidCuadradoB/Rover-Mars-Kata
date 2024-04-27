package org.david.cb.model.plateau;

import org.david.cb.model.Coordinates;
import org.david.cb.model.mower.Orientation;

public interface Plateau {

    Coordinates calculatePosition(Coordinates currentCoordinates, Orientation orientation);

    void addObstacle(Coordinates coordinates);

    boolean checkCoordinates(Coordinates coordinates);
}
