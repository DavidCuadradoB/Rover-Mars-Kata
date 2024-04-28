package org.david.cb.application.newmissionusecase;

import org.david.cb.application.mower.DeployMowerService;
import org.david.cb.application.mower.exceptions.IncorrectCommandForPlateauLimitsException;
import org.david.cb.application.plateau.CreatePlateauService;
import org.david.cb.model.commandreader.NewMissionCommandReader;
import org.david.cb.model.commandwriter.PositionWriter;
import org.david.cb.model.plateau.Plateau;

public class NewMissionUseCase {

    private final CreatePlateauService createPlateauService;
    private final DeployMowerService deployMowerService;
    private final NewMissionCommandReader newMissionCommandReader;
    private final PositionWriter positionWriter;

    public NewMissionUseCase(
            CreatePlateauService createPlateauService,
            DeployMowerService deployMowerService,
            NewMissionCommandReader newMissionCommandReader,
            PositionWriter positionWriter
    ) {
        this.createPlateauService = createPlateauService;
        this.deployMowerService = deployMowerService;
        this.newMissionCommandReader = newMissionCommandReader;
        this.positionWriter = positionWriter;
    }

    public void execute() throws IncorrectCommandForPlateauLimitsException {
        Plateau plateau = createPlateauService.createPlateau();
        do {
            deployMowerService.deploy(plateau)
                    .ifPresent(mower -> positionWriter.write(
                                    mower.getCoordinates().getX() + " " +
                                            mower.getCoordinates().getY() + " " +
                                            mower.getOrientation().abbreviation
                            )
                    );

        } while (
                !newMissionCommandReader.readExit().equals("exit")
        );

    }
}
