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
public class PlacePalaceCommand implements GameplayActionCommand {
    private TurnController controller;
    private TileComponent tile;
    private Space space;
    private SharedResources resources;


    public PlacePalaceCommand(TurnController controller, TileComponent tile, Space space, SharedResources resources){
        this.controller = controller;
        this.space = space;
        this.tile = tile;
        this.resources = resources;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void accept(CommandSaveVisitor visitor) {

    }
}
