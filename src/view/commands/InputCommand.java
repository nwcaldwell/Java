package view.commands;

import view.ViewController;

public abstract class InputCommand {

    private ViewController viewController;

    public InputCommand(ViewController viewController) {
        this.viewController = viewController;
    }

    public abstract void execute();

    protected ViewController getViewController(){
        return viewController;
    }
}
