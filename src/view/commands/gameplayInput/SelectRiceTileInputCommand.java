// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.R;
import view.ViewController;
import view.commands.InputCommand;

public class SelectRiceTileInputCommand extends InputCommand {

    public SelectRiceTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().startPlacingTile( new R( ).buildTile(HexDirection.N) );
	}
}