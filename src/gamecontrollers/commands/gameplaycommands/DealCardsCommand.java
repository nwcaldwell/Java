package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.board.TileComponentContents.Palace;
import models.palacefestival.Deck;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssyyddnneeyy on 4/22/14.
 */
public class DealCardsCommand implements GameplayActionCommand {
    JavaPlayer player;
    Deck deck;
    List<PalaceCard> cards;

    public DealCardsCommand(JavaPlayer p, Deck d){
        player = p;
        deck = d;
        cards = new ArrayList<PalaceCard>(3);
    }

    @Override
    public void execute() {
        cards.add(deck.drawFromDeck());
        cards.add(deck.drawFromDeck());
        cards.add(deck.drawFromDeck());

        for(PalaceCard card : cards){
            player.drawCard(card);
        }
    }

    @Override
    public void undo() {
        for(PalaceCard card : cards) {
            deck.returnCard(card);
            player.useCard(card);
        }
    }

    @Override
    public void accept(CommandSaveVisitor visitor) {

    }
}
