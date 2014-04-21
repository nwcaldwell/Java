// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.palacefestival.FestivalTurnController;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalPlayer;

public class EndFestivalTurnCommand implements GameplayActionCommand {
    private FestivalTurnController turnController;
    private FestivalPlayer player;
    private int playerIndex;

    public EndFestivalTurnCommand(FestivalTurnController tc, FestivalPlayer p, int index){
        turnController = tc;
        player = p;
        playerIndex = index;
    }

	@Override
    public void execute() {
        turnController.endTurn();
	}
	@Override	public void undo() {
		turnController.undoEndTurn(player, playerIndex);
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}