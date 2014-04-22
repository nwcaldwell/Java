package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;
import view.commands.InputCommand;

/**
 * Created by ssyyddnneeyy on 4/20/14.
 */
public class CancelCurrentActionInputCommand extends InputCommand{

    public CancelCurrentActionInputCommand(ViewController viewController){
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().cancelCurrentCommand();
        throw new UnsupportedOperationException();
    }
}
