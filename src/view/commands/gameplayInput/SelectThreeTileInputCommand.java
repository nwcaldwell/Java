// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.RVR;
import view.ViewController;
import view.commands.InputCommand;
import view.commands.JavaKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectThreeTileInputCommand extends InputCommand {
    //this is the set of keylisteners that will do stuff when stuff happens
    private List<JavaKeyListener> keySet;

    public SelectThreeTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().startPlacingTile( new RVR( ).buildTile(HexDirection.N) );
        getViewController().removeCurrentKeyListeners();
        getViewController().addKeyListeners(keySet);
        getViewController().getBoardview().update();
        getViewController().getBoardview().addTiles(Facade.getInstance().getCurrentTileComponent(),Facade.getInstance().getTilePlacementPath());
	}

    public void setKeySet(List<JavaKeyListener> newKeySet) {
        keySet = newKeySet;
    }

}