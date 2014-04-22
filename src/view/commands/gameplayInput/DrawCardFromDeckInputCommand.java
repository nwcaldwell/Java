// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;
import view.commands.InputCommand;

public class DrawCardFromDeckInputCommand extends InputCommand {

    public DrawCardFromDeckInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {

        Facade.getInstance().drawCardFromDeck();
	}
}