// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.commands.InputCommand;

public class StartFestivalInputCommand implements InputCommand {

	@Override	public void execute() {
        Facade.getInstance().startFestival();
	}
}