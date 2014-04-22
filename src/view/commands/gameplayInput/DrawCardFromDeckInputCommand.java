// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class DrawCardFromDeckInputCommand extends GameplayInputCommand {

    public DrawCardFromDeckInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().drawCardFromDeck();
    }
}