// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Message;
import gamecontrollers.Response;
import view.ViewController;

import java.util.List;

public class EndTurnInputCommand extends GameplayInputCommand {

    public EndTurnInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Response response =  Facade.getInstance().askForFestival();
        List<Message> messages = response.getMessages();
        
        if(getViewController().requireInputFromUser(messages.get(0).getMessageTemplate(), "Palace Festival", messages.get(0).isError())){
            response = Facade.getInstance().endTurn();
        }
        getViewController().displayMessageToConsole(response);
	}
}