// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.P;
import view.ViewController;
import view.commands.InputCommand;

public class SelectPalaceTileInputCommand extends InputCommand {

    public SelectPalaceTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().startPlacingTile( new P( ).buildTile(HexDirection.N) );
	}
}