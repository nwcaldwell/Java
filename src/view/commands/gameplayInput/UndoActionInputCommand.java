package view.commands.gameplayInput;

import view.ViewController;
import view.commands.InputCommand;

/**
 * Created by ssyyddnneeyy on 4/20/14.
 */
public class UndoActionInputCommand extends InputCommand {

    public UndoActionInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        throw new UnsupportedOperationException();
    }
}