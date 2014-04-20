package gamecontrollers.palacefestival;

import models.palacefestival.PalaceCard;

import java.util.List;

/**
 * Created by ssyyddnneeyy on 4/19/14.
 */
public class FestivalCardController {
    private List<PalaceCard> palaceCards;
    private PalaceCard currentCard;

    public FestivalCardController(List<PalaceCard> cards){
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
