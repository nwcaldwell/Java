package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.board.Space;
import models.board.TileComponent;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class PlaceTwoTileCommand implements GameplayActionCommand {
    private TurnController controller;
    private TileComponent tile;
    private Space space;

    public PlaceTwoTileCommand(TurnController controller, TileComponent tile, Space space){
        this.controller = controller;
        this.space = space;
        this.tile = tile;
    }
    @Override
    public void execute() {
        //this code is not copypasta
        //place that tile boiiiii
        space.placeTile(tile);
        //update player counter
        controller.getCurrentPlayer().playTwoTile();
        //update dat turn counter
        controller.playTile();

    }

    @Override
    public void undo() {
        //this code is not copypasta
        //(un)place that tile boiiiii
        space.placeTile(tile);
        //update player counter
        controller.getCurrentPlayer().returnTwoTile();
        //update dat turn counter
        controller.removeTile();
    }

    @Override
    public void accept(CommandSaveVisitor visitor) {

    }
}
