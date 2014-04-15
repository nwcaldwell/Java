package view.commands;

import view.View;
import view.ViewController;

public class NavCommand implements InputCommand {

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
