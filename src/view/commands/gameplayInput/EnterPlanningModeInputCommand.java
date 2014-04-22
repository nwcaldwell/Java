package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.HexDirection;
import view.ViewController;
import view.commands.JavaKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by kevinnieman on 4/21/14.
 */
public class EnterPlanningModeInputCommand extends GameplayInputCommand{

    public EnterPlanningModeInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().startPlanningMode();
    }
}
