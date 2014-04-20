package gamecontrollers.palacefestival;

import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;

public class FestivalLogicController {
    //this class is for checking if things are legal right?
    FestivalController festivalController;

	public FestivalLogicController(FestivalController controller) {
        festivalController = controller;
	}

    public boolean canParticipateInFestival(ArrayList<PalaceCard> cards, PalaceCard festivalCard){
        boolean canParticipate = false;

        for (PalaceCard card : cards){
            if(card.compare(festivalCard) > 0 ) return true;
        }
        return false;
    }

    public ArrayList<PalaceCard> getEligibleCards(ArrayList<PalaceCard> cards, PalaceCard festivalCard){
        ArrayList<PalaceCard> eligibleCards = new ArrayList<PalaceCard>();

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

    public void checkIfCanEndGame(){
        //it's the end of the round
        //can end the game if there's only one player left
        //if there's a tie and the players dont have any cards left
        //if there's a tie and the players have cards left - you have to ask the users if they want to end the game

    }

}