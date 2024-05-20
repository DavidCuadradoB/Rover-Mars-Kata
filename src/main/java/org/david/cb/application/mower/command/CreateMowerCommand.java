package org.david.cb.application.mower.command;

import org.david.cb.model.mower.Orientation;

public record CreateMowerCommand(int x, int y, Orientation orientation) {
}
