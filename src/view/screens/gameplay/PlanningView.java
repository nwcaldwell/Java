package view.screens.gameplay;

import models.board.HexDirection;
import view.ViewController;
import view.commands.JavaKeyListener;
import view.commands.gameplayInput.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

//TODO [Sydney][Jorge]

public class PlanningView extends GameplayView {

    public PlanningView(ViewController viewC) {
        super(viewC);
        initKeyListeners(viewC);
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {
    }

    private void initKeyListeners(ViewController viewController){
        // universal to normal game mode
        JavaKeyListener playExtraActionTokenInputCommand = new JavaKeyListener(KeyEvent.VK_T, new PlayExtraActionTokenInputCommand(viewController));
        JavaKeyListener drawCardFromDeckInputCommand = new JavaKeyListener(KeyEvent.VK_C , new DrawCardFromDeckInputCommand(viewController));
        JavaKeyListener drawFestivalCardInputCommand = new JavaKeyListener(KeyEvent.VK_F , new DrawFestivalCardInputCommand(viewController));
        JavaKeyListener commencePlayModeInputCommand = new JavaKeyListener(KeyEvent.VK_0, new CommencePlayModeInputCommand(viewController));

        //for undoing and re-doing
        JavaKeyListener undoActionInputCommand = new JavaKeyListener(KeyEvent.VK_DELETE, new UndoActionInputCommand(viewController));
        JavaKeyListener undoActionInputCommandOther = new JavaKeyListener(KeyEvent.VK_BACK_SPACE, new UndoActionInputCommand(viewController));

        // for tile and developer movement
        JavaKeyListener cancelCurrentActionInputCommand = new JavaKeyListener(KeyEvent.VK_ESCAPE, new CancelCurrentActionInputCommand(viewController));
        JavaKeyListener commitActionInputCommand = new JavaKeyListener(KeyEvent.VK_ENTER, new CommitActionInputCommand(viewController));

        // Move developer
        JavaKeyListener tabDeveloperInputCommand = new JavaKeyListener(KeyEvent.VK_TAB, new TabDeveloperInputCommand(viewController));
        JavaKeyListener moveDeveloperInputCommandSW = new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveDeveloperInputCommand(viewController, HexDirection.SW));
        JavaKeyListener moveDeveloperInputCommandS = new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveDeveloperInputCommand(viewController, HexDirection.S));
        JavaKeyListener moveDeveloperInputCommandSE = new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveDeveloperInputCommand(viewController, HexDirection.SE));
        JavaKeyListener moveDeveloperInputCommandNW = new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveDeveloperInputCommand(viewController, HexDirection.NW));
        JavaKeyListener moveDeveloperInputCommandN = new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveDeveloperInputCommand(viewController, HexDirection.N));
        JavaKeyListener moveDeveloperInputCommandNE = new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveDeveloperInputCommand(viewController, HexDirection.NE));

        // Move Tile
        JavaKeyListener rotateTileInputCommand = new JavaKeyListener(KeyEvent.VK_SPACE, new RotateTileInputCommand(viewController));
        JavaKeyListener moveTileInputCommandSW = new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveTileInputCommand(viewController, HexDirection.SW));
        JavaKeyListener moveTileInputCommandS = new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveTileInputCommand(viewController, HexDirection.S));
        JavaKeyListener moveTileInputCommandSE = new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveTileInputCommand(viewController, HexDirection.SE));
        JavaKeyListener moveTileInputCommandNW = new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveTileInputCommand(viewController, HexDirection.NW));
        JavaKeyListener moveTileInputCommandN = new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveTileInputCommand(viewController, HexDirection.N));
        JavaKeyListener moveTileInputCommandNE = new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveTileInputCommand(viewController, HexDirection.NE));

        //define commands first so i can set their key listeners after
        SelectIrrigationTileInputCommand sitc = new SelectIrrigationTileInputCommand(viewController);
        SelectPalaceTileInputCommand sptc = new SelectPalaceTileInputCommand(viewController);
        SelectRiceTileInputCommand srtc = new SelectRiceTileInputCommand(viewController);
        SelectThreeTileInputCommand s3tc = new SelectThreeTileInputCommand(viewController);
        SelectTwoTileInputCommand s2tc = new SelectTwoTileInputCommand(viewController);
        SelectVillageTileInputCommand svtc = new SelectVillageTileInputCommand(viewController);

        // Key sets switches
        SelectDeveloperInputCommand selectDeveloperInputCommand = new SelectDeveloperInputCommand(viewController);
        JavaKeyListener selectDeveloperKeyListener = new JavaKeyListener(KeyEvent.VK_D, selectDeveloperInputCommand);
        JavaKeyListener selectIrrigationTileInputCommand = new JavaKeyListener(KeyEvent.VK_I, sitc);
        JavaKeyListener selectPalaceTileInputCommand = new JavaKeyListener(KeyEvent.VK_P, sptc);
        JavaKeyListener selectRiceTileInputCommand = new JavaKeyListener(KeyEvent.VK_R, srtc);
        JavaKeyListener selectThreeTileInputCommand = new JavaKeyListener(KeyEvent.VK_3, s3tc);
        JavaKeyListener selectTwoTileInputCommand = new JavaKeyListener(KeyEvent.VK_2, s2tc);
        JavaKeyListener selectVillageTileInputCommand = new JavaKeyListener(KeyEvent.VK_V, svtc);

        selectDeveloperInputCommand.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveDeveloperInputCommandN,
                moveDeveloperInputCommandNE, moveDeveloperInputCommandNW, moveDeveloperInputCommandS, moveDeveloperInputCommandSE,
                moveDeveloperInputCommandSW, selectDeveloperKeyListener, selectVillageTileInputCommand, selectPalaceTileInputCommand,
                selectRiceTileInputCommand, selectThreeTileInputCommand, selectTwoTileInputCommand, selectIrrigationTileInputCommand,
                undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        sitc.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveTileInputCommandN,
                moveTileInputCommandNE, moveTileInputCommandNW, moveTileInputCommandS, moveTileInputCommandSE, rotateTileInputCommand,
                moveTileInputCommandSW, selectDeveloperKeyListener, selectVillageTileInputCommand, selectPalaceTileInputCommand,
                selectRiceTileInputCommand, selectThreeTileInputCommand, selectTwoTileInputCommand, selectIrrigationTileInputCommand,
                undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        sptc.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveTileInputCommandN,
                moveTileInputCommandNE, moveTileInputCommandNW, moveTileInputCommandS, moveTileInputCommandSE, rotateTileInputCommand,
                moveTileInputCommandSW, selectDeveloperKeyListener, selectVillageTileInputCommand,
                selectRiceTileInputCommand, selectThreeTileInputCommand, selectTwoTileInputCommand, selectIrrigationTileInputCommand,
                selectPalaceTileInputCommand, undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        srtc.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveTileInputCommandN,
                moveTileInputCommandNE, moveTileInputCommandNW, moveTileInputCommandS, moveTileInputCommandSE, rotateTileInputCommand,
                moveTileInputCommandSW, selectDeveloperKeyListener, selectVillageTileInputCommand, selectPalaceTileInputCommand,
                selectThreeTileInputCommand, selectTwoTileInputCommand, selectIrrigationTileInputCommand, selectRiceTileInputCommand,
                undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        s3tc.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveTileInputCommandN,
                moveTileInputCommandNE, moveTileInputCommandNW, moveTileInputCommandS, moveTileInputCommandSE, rotateTileInputCommand,
                moveTileInputCommandSW, selectDeveloperKeyListener, selectVillageTileInputCommand, selectPalaceTileInputCommand,
                selectRiceTileInputCommand, selectTwoTileInputCommand, selectIrrigationTileInputCommand, selectThreeTileInputCommand,
                undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        s2tc.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveTileInputCommandN,
                moveTileInputCommandNE, moveTileInputCommandNW, moveTileInputCommandS, moveTileInputCommandSE, rotateTileInputCommand,
                moveTileInputCommandSW, selectDeveloperKeyListener, selectVillageTileInputCommand, selectPalaceTileInputCommand,
                selectRiceTileInputCommand, selectThreeTileInputCommand, selectIrrigationTileInputCommand, selectTwoTileInputCommand,
                undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        svtc.setKeySet(getKeySet(playExtraActionTokenInputCommand, drawCardFromDeckInputCommand, drawFestivalCardInputCommand,
                cancelCurrentActionInputCommand, commitActionInputCommand, tabDeveloperInputCommand, moveTileInputCommandN,
                moveTileInputCommandNE, moveTileInputCommandNW, moveTileInputCommandS, moveTileInputCommandSE, rotateTileInputCommand,
                moveTileInputCommandSW, selectDeveloperKeyListener, selectPalaceTileInputCommand,
                selectRiceTileInputCommand, selectThreeTileInputCommand, selectTwoTileInputCommand, selectIrrigationTileInputCommand,
                selectVillageTileInputCommand, undoActionInputCommand, undoActionInputCommandOther, commencePlayModeInputCommand));

        keyListeners.addAll(selectDeveloperInputCommand.getKeySet());

    }

    private List<JavaKeyListener> getKeySet(JavaKeyListener... keyListeners){
        List<JavaKeyListener> keySet = new ArrayList<JavaKeyListener>();

        for( JavaKeyListener keyListener:keyListeners){
            keySet.add(keyListener);
        }

        return keySet;
    }
}
