// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.palacefestival.FestivalTurnController;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalPlayer;

public class DropOutOfFestivalCommand implements GameplayActionCommand {
    private FestivalTurnController controller;
    private FestivalPlayer player;
    private int index;

    public DropOutOfFestivalCommand(FestivalTurnController fc, FestivalPlayer p, int i){
        this.controller = fc;
        this.player = p;
        this.index = i;
    }

	@Override	public void execute() {
        controller.dropOut();
	}
	@Override	public void undo() {
		controller.undoDropOut(player, index);
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}