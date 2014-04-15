// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.board.Developer;
import models.board.Space;

public class MoveDeveloperCommand implements GameplayActionCommand {
    private Developer developer;
    private Space origin;
    private Space destination;

    public MoveDeveloperCommand(Developer d, Space o, Space dest) {
        this.developer = d;
        this.origin = o;
        this.destination = dest;
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