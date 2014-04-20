// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;
import view.commands.InputCommand;

public class AskForTieFestivalInputCommand extends InputCommand {

    public AskForTieFestivalInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().askForPalaceFestivalTie();
	}
}