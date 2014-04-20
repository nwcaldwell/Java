package models.palacefestival;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Library {
    private Stack<PalaceCard> cards;
	
	public Library(){
        this.cards = new Stack<PalaceCard>();

	}

    public Library(List<PalaceCard> cardList){
        cards = new Stack<PalaceCard>();
        shuffle(cardList);
    }

    public PalaceCard drawTopCard(){
        return cards.pop();
    }

    public boolean checkIfEmpty(){
        return cards.isEmpty();
    }

    public void shuffle(List<PalaceCard> newCards){
        cards.addAll(newCards);
        Collections.shuffle(cards);
    }

    public void returnCard(PalaceCard card){
        cards.push(card);
    }

    public List<PalaceCard> getCards(){
        ArrayList<PalaceCard> deckCopy = new ArrayList<PalaceCard>(cards);
       return deckCopy;
    }
}