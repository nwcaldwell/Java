// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import view.ViewController;
import view.commands.InputCommand;

public class DrawCardFromDeckInputCommand extends InputCommand {

    public DrawCardFromDeckInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        System.out.println("Number palace cards before happening: "+Facade.getInstance().getCurrentPlayer().getPalaceCards().size());
        Response response = Facade.getInstance().drawCardFromDeck();
        System.out.println("Number palace cards: "+Facade.getInstance().getCurrentPlayer().getPalaceCards().size());
        getViewController().displayMessageToConsole(response);
	}
}