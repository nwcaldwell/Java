package gamecontrollers.palacefestival;

import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

public class FestivalLogicController {
    //this class is for checking if things are legal right?
    FestivalController festivalController;

	public FestivalLogicController(FestivalController controller) {
        festivalController = controller;
	}

    public boolean canParticipateInFestival(List<PalaceCard> cards, PalaceCard festivalCard){
        boolean canParticipate = false;

        for (PalaceCard card : cards){
            if(card.compare(festivalCard) > 0 ) return true;
        }
        return false;
    }

    public List<PalaceCard> getEligibleCards(List<PalaceCard> cards, PalaceCard festivalCard){
        List<PalaceCard> eligibleCards = new ArrayList<PalaceCard>();

        for(PalaceCard card : cards){
            if(card.compare(festivalCard) > 0){
                eligibleCards.add(card);
            }
        }

        return eligibleCards;
    }

    public boolean checkIfCanEndTurn(int playersBid, int highestBid){
        if(playersBid >= highestBid)
            return true;
        return false;
    }

}