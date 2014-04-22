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
        System.out.println("Number palace cards before happening: "+Facade.getInstance().getCurrentPlayer().getPalaceCards().size());
        Response response = Facade.getInstance().drawCardFromDeck();
        System.out.println("Number palace cards: "+Facade.getInstance().getCurrentPlayer().getPalaceCards().size());
        getViewController().displayMessageToConsole(response);
	}
}