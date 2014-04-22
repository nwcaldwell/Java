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

public class SelectThreeTileInputCommand extends GameplayInputCommand {
    //this is the set of keylisteners that will do stuff when stuff happens
    private List<JavaKeyListener> keySet;

    public SelectThreeTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().startPlacingTile(new RVR().buildTile(HexDirection.N));
        getViewController().removeCurrentKeyListeners();
        getViewController().addKeyListeners(keySet);
	}

    public void setKeySet(List<JavaKeyListener> newKeySet) {
        keySet = newKeySet;
    }

}