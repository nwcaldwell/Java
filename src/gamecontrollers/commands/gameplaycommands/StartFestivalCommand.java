// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.save.CommandSaveVisitor;
import models.board.Space;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class StartFestivalCommand implements GameplayActionCommand {
    private JavaPlayer[] playersEligible;
    private Space palace;
    private PalaceCard festivalCard;

    public StartFestivalCommand(JavaPlayer[] players, Space p, PalaceCard fest){
        this.playersEligible = players;
        this.palace = p;
        this.festivalCard = fest;
    }

	@Override	public void execute() {
        Facade.getInstance().startNewFestival(playersEligible, festivalCard, palace);
	}
	@Override	public void undo() {
		throw new UnsupportedOperationException();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}