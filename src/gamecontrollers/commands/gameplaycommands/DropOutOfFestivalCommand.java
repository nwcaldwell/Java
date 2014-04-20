// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalPlayer;

public class DropOutOfFestivalCommand implements GameplayActionCommand {
    private FestivalController controller;
    private FestivalPlayer player;
    private int index;

    public DropOutOfFestivalCommand(FestivalController fc, FestivalPlayer p, int i){
        this.controller = fc;
        this.player = p;
        this.index = i;
    }

	@Override	public void execute() {
        controller.dropOutOfFestival();
	}
	@Override	public void undo() {
		controller.undoDropOutOfFestival(player, index);
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}