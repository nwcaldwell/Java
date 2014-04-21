// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.VR;
import view.ViewController;
import view.commands.InputCommand;

public class SelectTwoTileInputCommand extends InputCommand {

    public SelectTwoTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().startPlacingTile( new VR( ).buildTile(HexDirection.N) );
	}
}