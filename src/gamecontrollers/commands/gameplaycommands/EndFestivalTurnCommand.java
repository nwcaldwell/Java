// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalPlayer;

public class EndFestivalTurnCommand implements GameplayActionCommand {
    private FestivalPlayer player;

    public EndFestivalTurnCommand(FestivalPlayer p){
        this.player = p;
    }

	@Override	public void execute() {
		throw new UnsupportedOperationException();
	}
	@Override	public void undo() {
		throw new UnsupportedOperationException();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}