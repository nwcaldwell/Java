// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import view.ViewController;

public class DrawFestivalCardInputCommand extends GameplayInputCommand {

    public DrawFestivalCardInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Response response = Facade.getInstance().drawTheFestivalCard();
        getViewController().displayMessageToConsole(response);
	}
}