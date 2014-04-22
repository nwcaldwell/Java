// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Message;
import gamecontrollers.Response;
import view.ViewController;
import view.screens.gameplay.JavaFestivalView;

import java.util.List;

public class EndTurnInputCommand extends GameplayInputCommand {

    public EndTurnInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Response response =  Facade.getInstance().askForFestival();

        if(getViewController().requireInputFromUser("Would you like to begin a festival?", "Palace Festival", response.hasErrors())){
            Facade.getInstance().startFestival();
            getViewController().setCurrentView( new JavaFestivalView(getViewController()));
            response = Facade.getInstance().endTurn();
        }
        getViewController().displayMessageToConsole(response);
	}
}