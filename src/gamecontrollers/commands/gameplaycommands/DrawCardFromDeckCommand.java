// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.Deck;
import models.palacefestival.DeckMemento;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class DrawCardFromDeckCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private PalaceCard card;
    private Deck deck;
    private DeckMemento oldDeck;


    //Set reference to player and deck for use later in execute
    public DrawCardFromDeckCommand(JavaPlayer p, Deck deck){
        this.player = p;
        this.deck = deck;
    }

    @Override	public void execute() {
        //save state of deck before execution
        oldDeck = deck.createMemento();
        //draw card from deck and store it for later
        card = deck.drawFromDeck();
        //give card to player now
        player.drawCard(card);
    }

    @Override	public void undo() {
        //remove card from players list of cards
        player.takeBackCard(card);
        //return state of deck to old state using memento
        deck.restoreFromMemento(oldDeck);
    }
    @Override	public void accept(CommandSaveVisitor visitor) {
        throw new UnsupportedOperationException();
    }
}