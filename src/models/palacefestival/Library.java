package models.palacefestival;

import java.util.Collections;
import java.util.Stack;

public class Library {
    private Stack<PalaceCard> cards;
	
	public Library(){
        this.cards = new Stack<PalaceCard>();

	}

    public PalaceCard draw(){
        return cards.pop();
    }

    public boolean checkIfEmpty(){
        return cards.isEmpty();
    }

    public void shuffle(Stack<PalaceCard> newCards){
        cards.addAll(newCards);
        Collections.shuffle(cards);
    }

    public void returnCard(PalaceCard card){
        cards.push(card);
    }
}