package models.palacefestival;

import java.util.List;
import java.util.Stack;

public class Graveyard {
    private Stack<PalaceCard> discardedCards;
	
	public Graveyard(){
        this.discardedCards = new Stack<PalaceCard>();
	}

    public Graveyard(List<PalaceCard> cardList){
        this.discardedCards.addAll(cardList);
    }

    public void discard(PalaceCard card){
        discardedCards.push(card);
    }

    //returns the discarded cards for re-shuffling
    public Stack<PalaceCard> getDiscardedCards(){
        return discardedCards;
    }
}