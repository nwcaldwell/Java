package gamecontrollers.commandcreator;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.*;
import gamecontrollers.rules.tileplacementrules.TilePlacementRule;
import gamecontrollers.turn.TurnController;
import models.board.*;
import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Rice;
import models.board.TileComponentContents.Village;

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
    private HashSet<TileComponent> visitedTiles = new HashSet<TileComponent>();
    private Space space;
    private SharedResources resources;

     /*
   ========================================================================
      CONSTRUCTOR
   ========================================================================
    */

    public TileCreationVisitor(TurnController controller, SharedResources resources) {
        this.controller = controller;
        this.resources = resources;
    }

     /*
   ========================================================================
      PUBLIC METHODS
   ========================================================================
    */

    //this method will visit all the component and find how big the tile is
    public void visit(TileComponent component){


        Direction direction = component.getDirection();
        Iterator<Direction> it = direction.iterator();

        if(!visitedTiles.contains(component))
            visitedTiles.add(component);

        while(it.hasNext()) {
            Direction temp = it.next();
            if (component.siblingExists(temp) && !visitedTiles.add(component.getConjoinedTile(direction))){
                component.getConjoinedTile(temp).accept(this);
            }
        }
    }

    //this method will compute and return the command
    public GameplayActionCommand getCommand(TileComponent component){
        //check size of the component
        component.accept(this);


        if(visitedTiles.size() == 1){
            TileComponentContentVisitor visitor = new TileComponentContentVisitor();
            component.getTileComponentContent().accept(visitor);
        }
        else if( visitedTiles.size() == 2)
            command = new PlaceTwoTileCommand(controller, component, space);
        else
            command = new PlaceThreeTileCommand(controller, component, space, resources);

        //empty hash set for next command creation
        visitedTiles.clear();
        return command;
    }

    public void setSpace(Space s){
        this.space = s;
    }


     /*
   ========================================================================
       PRIVATE VISITOR
   ========================================================================
     */
    private class TileComponentContentVisitor implements TileVisitor{
        @Override
        public void visit(TileComponent tcc) {
            //This cant really happen
            command = null;
        }

        @Override
        public void visit(Palace p) {
           //THIS DOESNT GO HERE ANYMORE
           //go home palacetile, you're drunk
           // command = new PlacePalaceCommand(controller, new TileComponent(HexDirection.N, new Palace()), space, resources);
        }

        @Override
        public void visit(Village v) {
            //find out size of tile and then place it
            command = new PlaceVillageTileCommand(controller, new TileComponent(HexDirection.N, new Village()), space);
        }

        @Override
        public void visit(Rice r) {
            command = new PlaceRiceTileCommand(controller, new TileComponent(HexDirection.N, new Rice()), space);
        }

        @Override
        public void visit(Irrigation i) {
            command = new PlaceIrrigationTileCommand(controller, new TileComponent(HexDirection.N, new Irrigation()), space, resources);
        }
    }
}
