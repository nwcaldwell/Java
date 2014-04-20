// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;
import view.commands.InputCommand;

public class DropOutOfFestivalInputCommand extends InputCommand {

    public DropOutOfFestivalInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().dropOutOfFestival();
        getViewController().update();
	}
}