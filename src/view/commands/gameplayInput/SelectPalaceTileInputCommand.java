// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import models.board.HexTiles.P;
import view.ViewController;
import view.commands.JavaKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectPalaceTileInputCommand extends GameplayInputCommand {
    //this is the set of keylisteners that will do stuff when stuff happens
    private List<JavaKeyListener> keySet;

    public SelectPalaceTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().startPlacingTile( new P( ).buildTile(HexDirection.N) );
        getViewController().removeCurrentKeyListeners();
        getViewController().addKeyListeners(keySet);
        getViewController().getBoardview().update();
        getViewController().getBoardview().addTiles(Facade.getInstance().getCurrentTileComponent(),Facade.getInstance().getTilePlacementPath());
    }

    public void setKeySet(List<JavaKeyListener> newKeySet) {
        keySet = newKeySet;
    }
}