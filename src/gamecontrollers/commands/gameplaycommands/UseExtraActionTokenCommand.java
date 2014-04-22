// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.palacefestival.JavaPlayer;

public class UseExtraActionTokenCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private TurnController controller;

    public UseExtraActionTokenCommand(JavaPlayer p, TurnController controller){
        this.player = p;
        this.controller = controller;
    }

	@Override	public void execute() {
		System.out.println("Playing token");
		player.playExtraActionToken();
        controller.playExtraActionToken();
	}
	@Override	public void undo() {
        player.returnExtraActionToken();
        controller.returnExtraActionToken();
    }
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}
