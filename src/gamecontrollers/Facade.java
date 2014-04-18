package gamecontrollers;

import gamecontrollers.commandcreator.DevMoveController;
import gamecontrollers.commandcreator.TilePlacementController;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.turn.HistoryChannelController;
import gamecontrollers.turn.PlanningModeCommandHandler;
import gamecontrollers.turn.ReplayController;
import gamecontrollers.turn.TurnController;
import models.board.*;
import models.palacefestival.JavaPlayer;

import java.util.List;

public class Facade {

    private static Facade FacadeInstance = new Facade();
    private TilePlacementController tilePlacementController;
    private BoardLogicController boardLogicController;
    private FestivalController festivalController;
    private HistoryChannelController historyChannelController;
    private DevMoveController devMoveController;
    private TurnController turnController;
    private ReplayController replayController;
    private PlanningModeCommandHandler planningModeCommandHandler;

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

    public boolean validPlacement(TileComponent tile, Space space){
        System.out.println("Facade.findShortestPath is not implemented yet");
        return false;
    }


    public void ASkForPalaceFestivalTie(){
        throw new UnsupportedOperationException();
    }

    public int findShortestPath(JavaPlayer p, Space origin, Space destination, List<Space> path){

        System.out.println("Facade.findShortestPath is not implemented yet");
        return 0;
    }

}
