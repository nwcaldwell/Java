package gamecontrollers.palacefestival;

import gamecontrollers.rules.Rule;
import gamecontrollers.turn.HistoryChannelController;
import models.board.Space;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

public class FestivalController {
    private FestivalLogicController logicController;
    private FestivalTurnController turnController;
    private List<Rule> rules;

    public FestivalController(HistoryChannelController hcc) {
        logicController = new FestivalLogicController(this);
        rules = new ArrayList<Rule>();
        rules.add(new )
        turnController = new FestivalTurnController(this, logicController, hcc, rules);
    }

    public FestivalTurnController getTurnController(){
        return turnController;
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

        turnController.startNewFestival(festivalPlayers, new FestivalModel(festivalPlayers, festivalCard, palaceValue));
    }

    //TODO is this necessary?
    public void endFestival(ArrayList<FestivalPlayer> winners){
        // when there is a tie and the users want to end the festival,
        // when no one has cards left and they have to end the festival (don't want to ask if the user wants to end)
    }

}