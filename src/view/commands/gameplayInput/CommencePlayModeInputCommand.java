package view.commands.gameplayInput;

import view.ViewController;
import view.screens.gameplay.PlayView;

/**
 * Created by ssyyddnneeyy on 4/22/14.
 */
public class CommencePlayModeInputCommand extends GameplayInputCommand {

    public CommencePlayModeInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        //TODO other stuff
        System.out.println("switching to play mode");
        getViewController().setCurrentView(new PlayView(getViewController()));
    }
}
