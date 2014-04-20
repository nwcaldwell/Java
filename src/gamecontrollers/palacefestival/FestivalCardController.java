package gamecontrollers.palacefestival;

import models.palacefestival.PalaceCard;

import java.util.ArrayList;

/**
 * Created by ssyyddnneeyy on 4/19/14.
 */
public class FestivalCardController {
    private ArrayList<PalaceCard> palaceCards;
    private PalaceCard currentCard;

    public FestivalCardController(ArrayList<PalaceCard> cards){
        palaceCards = cards;
        resetSelectedCard();
    }

    public void incrementCurrentCard(){
        int index = palaceCards.indexOf(currentCard);
        currentCard = palaceCards.get((index+1) % palaceCards.size());
    }

    public PalaceCard getCurrentCard(){
        return currentCard;
    }

    public void resetSelectedCard(){
        currentCard = palaceCards.get(0);
    }

}
