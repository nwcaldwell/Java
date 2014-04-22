// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.board.TileComponentContents.Palace;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.List;

public class StartFestivalCommand implements GameplayActionCommand {
    private List<JavaPlayer> playersEligible;
    private Palace palace;
    private PalaceCard festivalCard;

    public StartFestivalCommand(List<JavaPlayer> players, Palace p, PalaceCard fest){
        this.playersEligible = players;
        this.palace = p;
        this.festivalCard = fest;
    }

	@Override	public void execute() {
        Facade.getInstance().startFestival(playersEligible, festivalCard, palace);
	}
	@Override	public void undo() {
		throw new UnsupportedOperationException();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}