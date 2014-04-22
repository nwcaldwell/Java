package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class UndoActionInputCommand extends GameplayInputCommand {

    public UndoActionInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().undoCommand();
    }
}
