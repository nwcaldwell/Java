package gamecontrollers.save;

import java.util.List;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.turn.HistoryChannelController;

/**
 * Created by jorgep on 4/14/14.
 */
public class SaveController {
	
	CommandSaveVisitor visitor;
	
	public SaveController(HistoryChannelController commandSource){
		visitor=new CommandSaveVisitor();
		List<GameplayActionCommand> commands = commandSource.getGameHistory();
		for (GameplayActionCommand command:commands){
			command.accept(visitor);
		}
	}
}
