// TODO developer [ Nathan ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.board.Space;
import models.board.TileComponent;

public class PlaceTileCommand implements GameplayActionCommand {
    private TileComponent tile;
    private Space space;
    private TurnController controller;

    public PlaceTileCommand(TileComponent t, Space s, TurnController controller){
        this.tile = t;
        this.space = s;
        this.controller = controller;
    }

	@Override	
	public void execute() {
		space.placeTile(tile);
        controller.playTile();
	}
	
	@Override	
	public void undo() {
		space.removeTile();
        controller.removeTile();
	}
	
	@Override	
	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}