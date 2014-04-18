// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.Deck;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class DrawFestivalCardCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private Deck deck;
    private PalaceCard card;

    public DrawFestivalCardCommand(JavaPlayer p, Deck deck){
        this.player = p;
        this.deck = deck;
    }


	@Override	public void execute() {
		//draw the festival card and store it
        card = deck.drawFestivalCard();
        //give the card to the player
        player.drawCard(card);

	}
	@Override	public void undo() {
        //put that card back onto the festival card
        player.takeBackCard(card);
        //replace the festival card on the deck
        deck.returnFestivalCard(card);
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}