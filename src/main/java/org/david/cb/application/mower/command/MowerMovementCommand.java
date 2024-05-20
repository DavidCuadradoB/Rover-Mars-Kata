package org.david.cb.application.mower.command;

import org.david.cb.model.mower.MowerCommand;

import java.util.List;

public record MowerMovementCommand(List<MowerCommand> movements) {
}
