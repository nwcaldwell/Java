// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.commands.InputCommand;

public class DrawCardFromDeckInputCommand implements InputCommand {

	@Override	public void execute() {
        Facade.getInstance().drawCardFromDeck();
	}
}