// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.I;
import view.ViewController;
import view.commands.InputCommand;

public class SelectIrrigationTileInputCommand extends InputCommand {

    public SelectIrrigationTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().startPlacingTile( new I( ).buildTile(HexDirection.N) );
	}
}