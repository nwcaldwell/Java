package gamecontrollers;

import gamecontrollers.commandcreator.DevMoveController;
import gamecontrollers.commandcreator.TilePlacementController;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.turn.HistoryChannelController;
import gamecontrollers.turn.PlanningController;
import gamecontrollers.turn.ReplayController;
import gamecontrollers.turn.TurnController;
import models.board.*;

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

    // getters
    public static Facade getInstance() {
        return FacadeInstance;
    }

    public static Board<HexSpace, HexTileComponent, HexDirection> getBoard(){
        throw new UnsupportedOperationException();
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

    // Festival Methods
    public void drawCardFromDeck() {
        throw new UnsupportedOperationException();
    }

    public void ASkForPalaceFestivalTie () {
        throw new UnsupportedOperationException();
    }

    // Actually execute the action being built
    // It returns a response that has messages for rules violation if any
    // if the action is executed successfully the response.hasErrors is set to true
    public Response doCommand(){
        throw new UnsupportedOperationException();
    }
}
