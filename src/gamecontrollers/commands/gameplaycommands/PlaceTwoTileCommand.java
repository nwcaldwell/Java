package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class PlaceTwoTileCommand implements GameplayActionCommand {
    private TurnController controller;

    public PlaceTwoTileCommand(TurnController controller){
        this.controller = controller;
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
