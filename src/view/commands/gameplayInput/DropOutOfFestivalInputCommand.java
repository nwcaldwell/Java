// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class DropOutOfFestivalInputCommand extends GameplayInputCommand {

    public DropOutOfFestivalInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().dropOutOfFestival();
	}
}