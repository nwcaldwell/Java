package gamecontrollers.palacefestival;

import gamecontrollers.turn.HistoryChannelController;
import models.board.TileComponentContents.Palace;
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

    public void startFestival(List<JavaPlayer> players, PalaceCard festivalCard, Palace palace){
        //convert the players into FestivalPlayers
        //the players passed in are the ones in the city
        //so need to check if their cards match the festival card

        List<FestivalPlayer> festivalPlayers = new ArrayList<FestivalPlayer>();

        for(int i = 0; i < players.size(); i++){
            System.out.println("traversing through the players");
            if(canParticipateInFestival(players.get(i).getPalaceCards(), festivalCard)){
                //they can participate in the festival
                System.out.println("adding the player to the festival");
                festivalPlayers.add(new FestivalPlayer(players.get(i), getEligibleCards(players.get(i).getPalaceCards(), festivalCard)));
                System.out.println("Number cards: "+festivalPlayers.get(i).getCards().size());
            }
        }

        System.out.println("Players: "+festivalPlayers.size());
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