// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalPlayer;

public class DropOutOfFestivalCommand implements GameplayActionCommand {
    private FestivalPlayer player;

    public DropOutOfFestivalCommand(FestivalPlayer p){
        this.player = p;
    }

	@Override	public void execute() {

        Facade.getInstance().dropCurrentPlayerFromFestival();
        throw new UnsupportedOperationException();
	}
	@Override	public void undo() {
		throw new UnsupportedOperationException();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}