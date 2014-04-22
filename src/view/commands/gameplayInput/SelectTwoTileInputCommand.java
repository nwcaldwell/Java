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

public class SelectTwoTileInputCommand extends GameplayInputCommand {
    //this is the set of keylisteners that will do stuff when stuff happens
    private ArrayList<JavaKeyListener> keyListeners =  new ArrayList<JavaKeyListener>(){{
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveTileInputCommand(getViewController(), HexDirection.SW)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveTileInputCommand(getViewController(), HexDirection.S)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveTileInputCommand(getViewController(), HexDirection.SE)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveTileInputCommand(getViewController(), HexDirection.NW)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveTileInputCommand(getViewController(), HexDirection.N)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveTileInputCommand(getViewController(), HexDirection.NE)));
    }};

    public SelectTwoTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().startPlacingTile( new VR( ).buildTile(HexDirection.N) );
        getViewController().removeCurrentKeyListeners();
        getViewController().addKeyListeners(keyListeners);
	}
}