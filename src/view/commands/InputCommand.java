package view.commands;

import view.ViewController;

public abstract class InputCommand {

    private ViewController viewController;

    public InputCommand(ViewController viewController) {
        this.viewController = viewController;
    }

    public abstract void execute();

    public ViewController getViewController() {
        return viewController;
    }

    protected void updateViewController() {
        viewController.update();
    }
}
