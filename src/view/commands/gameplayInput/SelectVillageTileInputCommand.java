// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.TileComponent;
import models.board.Village;
import view.commands.InputCommand;

public class SelectVillageTileInputCommand implements InputCommand {

	@Override	public void execute() {
        Facade.getInstance().startPlacingTile( new TileComponent(HexDirection.N, new Village() ) );
	}
}