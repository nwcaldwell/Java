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

    //empty the stack and return it
    public Stack<PalaceCard> takeDiscardedCards(){
        Stack<PalaceCard> temp = new Stack<PalaceCard>();
        Stack<PalaceCard> reverse = new Stack<PalaceCard>();

        while(!discardedCards.empty()){
            reverse.push(discardedCards.pop());
        }

        while(!reverse.empty()){
            temp.push(reverse.pop());
        }

        return temp;
    }

    public void undoDiscardCards(int numCards){
        for(int i = 0; i < numCards; i++){
            discardedCards.pop();
        }
    }
}