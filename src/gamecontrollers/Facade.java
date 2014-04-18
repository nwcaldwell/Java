package gamecontrollers;

import gamecontrollers.commandcreator.DevMoveController;
import gamecontrollers.commandcreator.TilePlacementController;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.turn.HistoryChannelController;
import gamecontrollers.turn.PlanningController;
import gamecontrollers.turn.ReplayController;
import gamecontrollers.turn.TurnController;
import models.board.Developer;
import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;

public class Facade {

    private static Facade FacadeInstance = new Facade();
    private TilePlacementController tilePlacementController;
    private BoardLogicController boardLogicController;
    private FestivalController festivalController;
    private HistoryChannelController historyChannelController;
    private DevMoveController devMoveController;
    private TurnController turnController;
    private ReplayController replayController;
    private PlanningController planningController;

    private Facade() {

    }
    public static Facade getInstance() {
        return FacadeInstance;
    }

    public void placeTileComponent(TileComponent tileComponent) {
        throw new UnsupportedOperationException();
    }

    public void placeDeveloper(Developer direction, Space space){
        throw new UnsupportedOperationException();
    }

    public void moveTile(Direction direction){
        throw new UnsupportedOperationException();
    }

    public void moveDeveloper(Direction direction){
        throw new UnsupportedOperationException();
    }

    public void planCommand(){
        throw new UnsupportedOperationException();
    }

    public void doCommand(){
        throw new UnsupportedOperationException();
    }

    // Festival Methods
    public void ASkForPalaceFestivalTie(){
        throw new UnsupportedOperationException();
    }
}
