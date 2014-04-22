package view.commands.gameplayInput;

import view.ViewController;
import view.screens.gameplay.PlanningView;

/**
 * Created by ssyyddnneeyy on 4/22/14.
 */
public class CommencePlanningModeInputCommand extends  GameplayInputCommand{

    public CommencePlanningModeInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        //TODO other stuff
        System.out.println("switching to planning mode");
        getViewController().setCurrentView(new PlanningView(getViewController()));

    }
}
