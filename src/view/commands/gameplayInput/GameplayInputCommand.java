package view.commands.gameplayInput;

import view.ViewController;
import view.commands.InputCommand;

// TODO verify is need
public abstract class GameplayInputCommand extends InputCommand {

    public GameplayInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void execute() {
        doExecute();
        getViewController().update();
    }

    protected abstract void doExecute();
}
