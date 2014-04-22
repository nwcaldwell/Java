// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class DrawFestivalCardInputCommand extends GameplayInputCommand {

    public DrawFestivalCardInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().drawTheFestivalCard();
	}
}