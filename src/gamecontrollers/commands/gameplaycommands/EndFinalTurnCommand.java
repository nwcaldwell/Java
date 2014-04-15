// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.JavaPlayer;

public class EndFinalTurnCommand implements GameplayActionCommand {
    private int points;
    private JavaPlayer player;

    public EndFinalTurnCommand(int pointsEarned, JavaPlayer p){
        this.points = pointsEarned;
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