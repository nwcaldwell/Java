// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.Facade;
import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

public class EndFestivalCommand implements GameplayActionCommand {
    private List<PalaceCard> discardedCards;
    private List<FestivalPlayer> winners;
    private int pointsEarned;
    private FestivalModel model;

    public EndFestivalCommand(FestivalModel festivalModel, int points){
        this.discardedCards = festivalModel.getDiscardedCards();
        this.winners = festivalModel.getWinners();
        this.pointsEarned = points;
    }

    public List<JavaPlayer> getPlayersFromFestival(){
        List<JavaPlayer> players = new ArrayList<JavaPlayer>();
        for(int i = 0; i < winners.size(); i++){
            players.add(winners.get(i).getPlayer());
        }
        return players;
    }

	@Override	public void execute() {
        Facade.getInstance().endFestival(discardedCards, getPlayersFromFestival(), pointsEarned);
	}
	@Override	public void undo() {
		Facade.getInstance().undoEndFestival(discardedCards, getPlayersFromFestival(), pointsEarned);
        throw new UnsupportedOperationException();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}