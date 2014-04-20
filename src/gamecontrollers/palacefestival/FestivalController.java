package gamecontrollers.palacefestival;

import gamecontrollers.turn.HistoryChannelController;
import models.board.Space;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

public class FestivalController {
    private FestivalModel festivalModel;
    private FestivalLogicController logicController;
    private FestivalTurnController turnController;

    public FestivalController(HistoryChannelController hcc) {
        logicController = new FestivalLogicController(this);
        turnController = new FestivalTurnController(this, hcc);
    }

    public ArrayList<FestivalPlayer> getWinners(){
		return festivalModel.getWinners();
    }

    public FestivalTurnController getTurnController(){
        return turnController;
    }

    public void setFestivalModel(FestivalModel model){
        festivalModel = model;
    }

    public void startFestival(JavaPlayer[] players, PalaceCard festivalCard, Space palace, int palaceValue){
        //convert the players into FestivalPlayers
        //the players passed in are the ones in the city
        //so need to check if their cards match the festival card

        List<FestivalPlayer> festivalPlayers = new ArrayList<FestivalPlayer>();

        for(int i = 0; i < players.length; i++){
            if(logicController.canParticipateInFestival(players[i].getPalaceCards(), festivalCard)){
                //they can participate in the festival
                festivalPlayers.add(new FestivalPlayer(players[i], logicController.getEligibleCards(players[i].getPalaceCards(), festivalCard)));
            }
        }

        festivalModel = new FestivalModel(festivalPlayers, festivalCard, palaceValue);
        turnController.startNewFestival(festivalPlayers, festivalModel);

    }

    public void endFestival(){
        //need to make a command? to end the festival.
        //this will be called when there is only one person left,
        // when there is a tie and the users want to end the festival,
        // when no one has cards left and they have to end the festival (don't want to ask if the user wants to end)
    }

}