package view.commands;

import view.View;
import view.ViewController;



public class NavCommand extends InputCommand {

    private View viewToGoTo;

    public NavCommand(ViewController viewC, View view) {
        super(viewC);
        viewToGoTo = view;
    }

    @Override
    public void execute() {
        getViewController().setCurrentView( viewToGoTo );
    }


}
