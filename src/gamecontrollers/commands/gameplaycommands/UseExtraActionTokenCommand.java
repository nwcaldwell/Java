// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.JavaPlayer;

public class UseExtraActionTokenCommand implements GameplayActionCommand {
    private JavaPlayer player;

    public UseExtraActionTokenCommand(JavaPlayer p){
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