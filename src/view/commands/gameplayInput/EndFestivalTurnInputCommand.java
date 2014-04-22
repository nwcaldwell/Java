// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import view.ViewController;
import view.commands.InputCommand;

public class EndFestivalTurnInputCommand extends InputCommand {

    public EndFestivalTurnInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Response response = Facade.getInstance().endFestivalTurn();
        getViewController().update();
        getViewController().displayMessageToConsole(response);
    }
}