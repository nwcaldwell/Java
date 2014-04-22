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

public class SelectThreeTileInputCommand extends GameplayInputCommand {
    //this is the set of keylisteners that will do stuff when stuff happens
    private ArrayList<JavaKeyListener> keyListeners =  new ArrayList<JavaKeyListener>(){{
        add(new JavaKeyListener(KeyEvent.VK_ESCAPE, new CancelCurrentActionInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_ENTER, new CommitActionInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_TAB, new TabDeveloperInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_TAB, new TabPalaceInputCommand(getViewController())));

        add(new JavaKeyListener(KeyEvent.VK_C , new DrawCardFromDeckInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_F , new DrawFestivalCardInputCommand(getViewController())));

        add(new JavaKeyListener(KeyEvent.VK_X, new EndTurnInputCommand(getViewController())));



        add(new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveTileInputCommand(getViewController(), HexDirection.SW)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveTileInputCommand(getViewController(), HexDirection.S)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveTileInputCommand(getViewController(), HexDirection.SE)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveTileInputCommand(getViewController(), HexDirection.NW)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveTileInputCommand(getViewController(), HexDirection.N)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveTileInputCommand(getViewController(), HexDirection.NE)));

        add(new JavaKeyListener(KeyEvent.VK_T, new PlayExtraActionTokenInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_SPACE, new RotateTileInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_D, new SelectDeveloperInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_I, new SelectIrrigationTileInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_P, new SelectPalaceTileInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_R, new SelectRiceTileInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_2, new SelectTwoTileInputCommand(getViewController())));
        add(new JavaKeyListener(KeyEvent.VK_V, new SelectVillageTileInputCommand(getViewController())));
    }};

    public SelectThreeTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().startPlacingTile( new RVR( ).buildTile(HexDirection.N) );
        getViewController().removeCurrentKeyListeners();
        getViewController().addKeyListeners(keyListeners);
	}
}