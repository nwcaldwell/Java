package gamecontrollers;

import gamecontrollers.commandcreator.DevMoveController;
import gamecontrollers.commandcreator.TilePlacementController;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.turn.HistoryChannelController;
import gamecontrollers.turn.PlanningController;
import gamecontrollers.turn.ReplayController;
import gamecontrollers.turn.TurnController;
import models.board.*;

import java.util.List;

/**
 * Created by jorgep on 4/14/14.
 */
public class Facade {
    private TilePlacementController tpc;
    private BoardLogicController blc;
    private FestivalController fc;
    private HistoryChannelController hcc;
    private DevMoveController dmc;
    private TurnController tc;
    private ReplayController rc;
    private PlanningController pc;

    public Facade(){

    }

    public void placeTileComponent(TileComponent t){

    }

    public void placeDeveloper(Developer d, Space s){

    }

    public void moveTile(Direction d){

    }

    public void moveDeveloper(Direction d){

    }

    public void planCommand(){

    }

    public void doCommand(){

    }

    public boolean validPlacement(TileComponent t, Space s){

        return false;
    }

    public int findShortestPath(Player p, Space origin, Space destination, List<Space> path){

        return 0;
    }

}
