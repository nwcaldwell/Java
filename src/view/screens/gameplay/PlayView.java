package view.screens.gameplay;

import models.board.HexDirection;
import view.ViewController;
import view.commands.JavaButtonListener;
import view.commands.JavaKeyListener;
import view.commands.NavCommand;
import view.commands.gameplayInput.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//TODO [Sydney][Jorge]

public class PlayView extends GameplayView {

    /*
    ========================================================================
      Constructors
    ========================================================================
    */
    public PlayView(ViewController viewC) {
        super(viewC);
        initKeyListeners(viewC);
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {

        super.init();
    }

    private void initKeyListeners(ViewController viewController){
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ESCAPE, new CancelCurrentActionInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ENTER, new CommitActionInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_TAB, new TabDeveloperInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_TAB, new TabPalaceInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_C , new DrawCardFromDeckInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_F , new DrawFestivalCardInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_X, new EndTurnInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveDeveloperInputCommand(viewController, HexDirection.SW)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveDeveloperInputCommand(viewController, HexDirection.S)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveDeveloperInputCommand(viewController, HexDirection.SE)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveDeveloperInputCommand(viewController, HexDirection.NW)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveDeveloperInputCommand(viewController, HexDirection.N)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveDeveloperInputCommand(viewController, HexDirection.NE)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveTileInputCommand(viewController, HexDirection.SW)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveTileInputCommand(viewController, HexDirection.S)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveTileInputCommand(viewController, HexDirection.SE)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveTileInputCommand(viewController, HexDirection.NW)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveTileInputCommand(viewController, HexDirection.N)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveTileInputCommand(viewController, HexDirection.NE)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_T, new PlayExtraActionTokenInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_SPACE, new RotateTileInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_D, new SelectDeveloperInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_I, new SelectIrrigationTileInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_P, new SelectPalaceTileInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_R, new SelectRiceTileInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_3, new SelectThreeTileInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_2, new SelectTwoTileInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_V, new SelectVillageTileInputCommand(viewController)));
    }
}
