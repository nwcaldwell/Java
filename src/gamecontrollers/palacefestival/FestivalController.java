package gamecontrollers.palacefestival;

import models.board.Space;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;

public class FestivalController {
    private FestivalModel festivalModel;
    private FestivalLogicController logicController;
    private FestivalTurnController turnController;

    public FestivalController() {
        logicController = new FestivalLogicController();
        turnController = new FestivalTurnController();
    }

    public ArrayList<JavaPlayer> calculateWinners(){
		return null;}

    public void setFestivalModel(FestivalModel model){
        festivalModel = model;
    }

    public void endFestival(){}

    public void startFestival(JavaPlayer[] players, PalaceCard festivalCard, Space palace){
        //convert the players into FestivalPlayers
        //the players passed in are the ones in the city
        //so need to check if their cards match the festival card

        ArrayList<FestivalPlayer> festivalPlayers = new ArrayList<FestivalPlayer>();

//        for(int i = 0; i < players.length; i++){
//            if(logicController.checkIfCanParticipateInFestival(players[i], festivalCard)){
//
//                //they can participate in the festival
//                festivalPlayers.add(new FestivalPlayer(players[i], logicController.getEligibleCards(players[i].getPalaceCards(), festivalCard)));
//            }
//        }
//
//        festivalModel = new FestivalModel(festivalPlayers, festivalCard, )
    }

    public void playCard(PalaceCard card){

    }
}