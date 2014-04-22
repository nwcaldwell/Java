// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import view.ViewController;

public class DrawCardFromDeckInputCommand extends GameplayInputCommand {

    public DrawCardFromDeckInputCommand(ViewController viewController) {
        super(viewController);
    }


    @Override	public void doExecute() {
        Response response = Facade.getInstance().drawCardFromDeck();
        getViewController().displayMessageToConsole(response);
	}
}