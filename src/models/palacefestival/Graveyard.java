package models.palacefestival;

import java.util.Stack;

public class Graveyard {
    private Stack<PalaceCard> discardedCards;
	
	public Graveyard(){
        this.discardedCards = new Stack<PalaceCard>();
	}

    //adds a used card to the graveyard stack
    public void discard(PalaceCard card){
        discardedCards.push(card);
    }

    //returns the discarded cards for re-shuffling
    public Stack<PalaceCard> getDiscardedCards(){
        return discardedCards;
    }
}