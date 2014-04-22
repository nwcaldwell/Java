// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.VR;
import view.ViewController;
import view.commands.InputCommand;
import view.commands.JavaKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectTwoTileInputCommand extends GameplayInputCommand {
    //this is the set of keylisteners that will do stuff when stuff happens
    private List<JavaKeyListener> keySet;

    public SelectTwoTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().startPlacingTile( new VR( ).buildTile(HexDirection.N) );
        getViewController().removeCurrentKeyListeners();
        getViewController().addKeyListeners(keySet);
        getViewController().getBoardview().update();
        getViewController().getBoardview().addTiles(Facade.getInstance().getCurrentTileComponent(),Facade.getInstance().getTilePlacementPath());
    }

    public void setKeySet(List<JavaKeyListener> newKeySet) {
        keySet = newKeySet;
    }

}