package view.commands.gameplayInput;

import view.ViewController;
import view.screens.gameplay.ReplayView;

/**
 * Created by ssyyddnneeyy on 4/22/14.
 */
public class CommenceReplayModeInputCommand extends GameplayInputCommand {

    public CommenceReplayModeInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        //TODO other stuff
        getViewController().setCurrentView(new ReplayView(getViewController()));

    }
}
