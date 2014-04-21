// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.palacefestival.JavaPlayer;

public class EndTurnCommand implements GameplayActionCommand {
    private TurnController controller;

    public EndTurnCommand( TurnController controller) {
        this.controller = controller;
    }

	@Override	public void execute() {
	    controller.endTurn();
	}
	@Override	public void undo() {
		controller.revertTurn();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}