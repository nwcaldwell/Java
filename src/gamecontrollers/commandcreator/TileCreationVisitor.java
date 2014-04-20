package gamecontrollers.commandcreator;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.*;
import gamecontrollers.turn.TurnController;
import models.board.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class TileCreationVisitor {
    //This Visitor will figure out and create the appropriate command
    private GameplayActionCommand command;
    //things needed to create command
    private TurnController controller;
    private HashSet<TileComponent> visitedTiles;

    public TileCreationVisitor(TurnController controller) {
        this.controller = controller;
    }


    //this method will visit all the component and find how big the tile is
    public void visit(TileComponent component){


        Direction direction = component.getDirection();
        Iterator<Direction> it = direction.iterator();

        if(!visitedTiles.contains(component))
            visitedTiles.add(component);

        while(it.hasNext()) {
            Direction temp = it.next();
            if (component.siblingExists(temp)){
                component.getConjoinedTile(temp).accept(this);
            }
        }
    }

    public GameplayActionCommand getCommand(TileComponent component){
        //check size of the component
        component.accept(this);


        if(visitedTiles.size() == 1){
            TileComponentContentVisitor visitor = new TileComponentContentVisitor();
            component.getTileComponentContent().accept(visitor);
        }
        else if( visitedTiles.size() == 2)
            command = new PlaceTwoTileCommand(controller);
        else
            command = new PlaceThreeTileCommand(controller);




        return command;
    }

    private class TileComponentContentVisitor implements TileVisitor{
        @Override
        public void visit(TileComponent tcc) {
            //This cant really happen
            command = null;
        }

        @Override
        public void visit(Palace p) {
            command = new PlacePalaceCommand(controller);
        }

        @Override
        public void visit(Village v) {
            //find out size of tile and then place it
            command = new PlaceVillageTileCommand(controller);
        }

        @Override
        public void visit(Rice r) {
            command = new PlaceRiceTileCommand(controller);
        }

        @Override
        public void visit(Irrigation i) {
            command = new PlaceIrrigationTileCommand(controller);
        }
    }
}
