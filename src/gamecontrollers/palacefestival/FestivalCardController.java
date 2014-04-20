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
        currentCard = palaceCards.get(0);
    }

    public void incrementCurrentCard(){
        int index = palaceCards.indexOf(currentCard);
        currentCard = palaceCards.get((index+1) % palaceCards.size());
    }

    public PalaceCard getCurrentCard(){
        return currentCard;
    }

}
