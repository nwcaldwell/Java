// TODO developer [ Nathan ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.board.Space;
import models.board.TileComponent;

public class PlaceTileCommand implements GameplayActionCommand {
    private TileComponent tile;
    private Space space;

    public PlaceTileCommand(TileComponent t, Space s){
        this.tile = t;
        this.space = s;
    }

	@Override	
	public void execute() {
		space.placeTile(tile);
	}
	
	@Override	
	public void undo() {
		space.removeTile();
	}
	
	@Override	
	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}