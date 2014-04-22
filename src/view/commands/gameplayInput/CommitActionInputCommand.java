// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import view.ViewController;
import view.commands.InputCommand;

public class CommitActionInputCommand extends InputCommand {

    public CommitActionInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Response response = Facade.getInstance().commitMove();
        //TODO TO ONLY PRINT ERRORS
        getViewController().displayMessageToConsole(response);
	}
}