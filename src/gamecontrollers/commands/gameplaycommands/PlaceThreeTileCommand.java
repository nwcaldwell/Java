package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.board.SharedResources;
import models.board.Space;
import models.board.TileComponent;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class PlaceThreeTileCommand implements GameplayActionCommand{
    private TurnController controller;
    private TileComponent tile;
    private Space space;
    private SharedResources resources;

    public PlaceThreeTileCommand(TurnController controller, TileComponent tile, Space space, SharedResources resources){
        this.controller = controller;
        this.space = space;
        this.tile = tile;
        this.resources = resources;
    }
    @Override
    public void execute() {
        //this code is not copypasta
        //place that tile boiiiii
        space.placeTile(tile);
        //update resource counter
        resources.decNumThreeTiles();
        //update dat turn counter
        controller.playTile();

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
    }

    @Override
    public void accept(CommandSaveVisitor visitor) {

    }
}
