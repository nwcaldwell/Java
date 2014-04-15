package view.commands.gameplayInput;

import view.commands.Command;
import view.View;
import view.ViewController;

/**
 * Created by jorgep on 4/6/14.
 */
public class NavCommand extends Command {

    private ViewController viewController;
    private View viewToGoTo;

    public NavCommand(ViewController viewC, View view) {
        viewController = viewC;
        viewToGoTo = view;
    }

    @Override
    public void execute() {
        viewController.setCurrentView( viewToGoTo );
    }
}
