// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import gamecontrollers.turn.TurnController;
import models.palacefestival.Deck;
import models.palacefestival.DeckMemento;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class DrawCardFromDeckCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private PalaceCard card;
    private Deck deck;
    private DeckMemento oldDeck;
    private TurnController controller;


    //Set reference to player and deck for use later in execute
    public DrawCardFromDeckCommand(JavaPlayer p, Deck deck, TurnController controller)
    {
        this.player = p;
        this.deck = deck;
        this.controller = controller;
    }

    @Override	public void execute() {
        //save state of deck before execution
        if(oldDeck == null)
            oldDeck = deck.createMemento();
        else
            deck.restoreFromMemento(oldDeck);
        //draw card from deck and store it for later
        card = deck.drawFromDeck();
        //give card to player now
        player.drawCard(card);
        //update turn state
        controller.drawCard();
    }

    @Override	public void undo() {
        //remove card from players list of cards
        player.takeBackCard(card);
        //return state of deck to old state using memento
        deck.restoreFromMemento(oldDeck);
        //update TurnState
        controller.returnCard();
    }
    @Override	public void accept(CommandSaveVisitor visitor) {
        throw new UnsupportedOperationException();
    }
}