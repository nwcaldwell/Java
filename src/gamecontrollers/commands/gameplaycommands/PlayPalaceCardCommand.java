// TODO developer [ Sydney ], test [ Jorge ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;

public class PlayPalaceCardCommand implements GameplayActionCommand {
    private FestivalPlayer player;
    private PalaceCard card;

    public PlayPalaceCardCommand(FestivalPlayer p, PalaceCard c){
        this.player = p;
        this.card = c;
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