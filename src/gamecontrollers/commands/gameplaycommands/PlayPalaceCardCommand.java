// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;

public class PlayPalaceCardCommand implements GameplayActionCommand {
    private FestivalPlayer player;
    private FestivalModel model;
    private PalaceCard card;

    public PlayPalaceCardCommand(FestivalPlayer p, FestivalModel m, PalaceCard c){
        this.player = p;
        this.model = m;
        this.card = c;
    }

	@Override	public void execute() {
        player.playCard(card);
        model.addToPlayerBid(player, card);
	}
	@Override	public void undo() {
        player.undoPlayCard(card);
        model.undoAddToPlayerBid(player, card);
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}