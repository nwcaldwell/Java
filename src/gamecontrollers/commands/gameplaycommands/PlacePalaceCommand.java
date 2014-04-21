package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.board.*;

import java.util.List;

/*
    THIS ASSIGNS POINTS = HALF THE PALACE LEVEL.
    IN ORDER TO CHANGE THIS THE SUBCLASS THIS CLASS AND OVERRIDE SAID METHOD
 */

/**
 * Created by kevinnieman on 4/20/14.
 */
public class PlacePalaceCommand implements GameplayActionCommand {
    private TurnController controller;
    private int level;
    private Space space;
    private SharedResources resources;
    private List<TileComponentContent> city;

    public PlacePalaceCommand(TurnController controller, int level, Space space, SharedResources resources, List<TileComponentContent> city){
        this.controller = controller;
        this.space = space;
        this.level = level;
        this.resources = resources;
        this.city = city;
    }

    @Override
    public void execute() {
        TileComponent tile = new TileComponent(HexDirection.N, new Palace(level));
        //this code is not copypasta
        //place that tile boiiiii
        space.placeTile(tile);
        //update resource counter
        resources.decNumThreeTiles();
        //update dat turn counter
        controller.playTile();

        //special palace stuff for score
        controller.getCurrentPlayer().alterFamePoints(level/2);

        //special palace stuff to flop canAcceptPalace
        for(TileComponentContent comp : city){
            comp.setCanAcceptPalace(false);
        }

    }

    @Override
    public void undo() {
        //this code is not copypasta
        //(un)place that tile boiiiii
        space.removeTile();
        //update resource counter
        resources.incNumThreeTiles();
        //update dat turn counter
        controller.removeTile();

        //special palace stuff for score
        controller.getCurrentPlayer().alterFamePoints(-level/2);

        //special palace stuff to flop canAcceptPalace
        for(TileComponentContent comp : city){
            comp.setCanAcceptPalace(true);
        }
    }


    @Override
    public void accept(CommandSaveVisitor visitor) {

    }
}
