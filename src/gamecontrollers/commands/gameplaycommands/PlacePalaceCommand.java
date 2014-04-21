package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.board.*;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class PlacePalaceCommand implements GameplayActionCommand {
    private TurnController controller;
    private int level;
    private Space space;
    private SharedResources resources;

    public PlacePalaceCommand(TurnController controller, int level, Space space, SharedResources resources){
        this.controller = controller;
        this.space = space;
        this.level = level;
        this.resources = resources;
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
