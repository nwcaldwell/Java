package gamecontrollers.palacefestival;

import gamecontrollers.rules.PalaceFestivalRules.PlayerCanEndTurn;
import gamecontrollers.rules.Rule;
import gamecontrollers.turn.HistoryChannelController;
import models.board.Palace;
import models.board.Space;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

public class FestivalController {
    private FestivalTurnController turnController;

    public FestivalController(HistoryChannelController hcc) {
        turnController = new FestivalTurnController(hcc);
    }

    public FestivalTurnController getTurnController(){
        return turnController;
    }

    public void startFestival(JavaPlayer[] players, PalaceCard festivalCard, Palace palace){
        //convert the players into FestivalPlayers
        //the players passed in are the ones in the city
        //so need to check if their cards match the festival card

        List<FestivalPlayer> festivalPlayers = new ArrayList<FestivalPlayer>();

        for(int i = 0; i < players.length; i++){
            if(canParticipateInFestival(players[i].getPalaceCards(), festivalCard)){
                //they can participate in the festival
                festivalPlayers.add(new FestivalPlayer(players[i], getEligibleCards(players[i].getPalaceCards(), festivalCard)));
            }
        }

        turnController.startNewFestival(new FestivalModel(festivalPlayers, festivalCard, palace));
    }

    public boolean canParticipateInFestival(List<PalaceCard> cards, PalaceCard festivalCard){
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

}