package models.palacefestival;

import java.util.Stack;

public class Graveyard {
    private Stack<PalaceCard> discardedCards;
	
	public Graveyard(){

	}

    public void discard(PalaceCard card){
        discardedCards.push(card);
    }

    public Stack<PalaceCard> getDiscardedCards(){
        return discardedCards;
    }
}