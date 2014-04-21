// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class TabPalaceInputCommand extends GameplayInputCommand {

    public TabPalaceInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void doExecute() {
		Facade.getInstance().tabThroughPalace();
    }
}