// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.board.Developer;
import models.board.Space;

public class DevMoveCommand implements GameplayActionCommand {
    private Developer developer;
    private Space origin;
    private Space destination;
    private TurnController controller;

    public DevMoveCommand(Developer d, Space o, Space dest, TurnController turn) {
        this.developer = d;
        this.origin = o;
        this.destination = dest;
    }

	@Override
    public void execute() {
		developer.updateLocation(destination);
	}
    @Override
    public void undo() {
        developer.updateLocation(origin);
    }
    @Override
    public void accept(CommandSaveVisitor visitor) {
        visitor.visit(this);
    }


}