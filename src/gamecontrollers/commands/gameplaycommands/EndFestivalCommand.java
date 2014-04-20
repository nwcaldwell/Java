// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalModel;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.List;

public class EndFestivalCommand implements GameplayActionCommand {
    private List<PalaceCard> discardedCards;
    private List<JavaPlayer> winners;
    private int pointsEarned;
    private FestivalModel model;

    public EndFestivalCommand(List<PalaceCard> discard, List<JavaPlayer> w, int points, FestivalModel festivalModel){
        this.discardedCards = discard;
        this.winners = w;
        this.pointsEarned = points;
        this.model = festivalModel;
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