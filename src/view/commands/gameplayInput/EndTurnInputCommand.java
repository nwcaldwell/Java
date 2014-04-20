// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class EndTurnInputCommand extends GameplayInputCommand {

    public EndTurnInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().endTurn();
	}
}