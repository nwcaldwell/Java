package gamecontrollers.palacefestival;

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

    public FestivalController() {
        logicController = new FestivalLogicController(this);
        turnController = new FestivalTurnController(this);
    }

    public ArrayList<FestivalPlayer> getWinners(){
		return festivalModel.getWinners();
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

        turnController.startNewFestival(festivalPlayers);
        festivalModel = new FestivalModel(festivalPlayers, festivalCard, palaceValue);
    }

    public void playCard(PalaceCard card){
        //remove the card from the player's stash, and put it into the discarded pile
        //add the card to the deck's graveyard
        //increment the current player's bid
        //check if the bid is higher than the highest bid in the model, if so update it
    }

    public void dropOutOfFestival(){
        //mark the user as being dropped out
        //increment to the next players turn
    }

    public void endTurn(){
        //check if the user is allowed to end the turn
        //if so then end turn
        //if not, force to drop out
    }

    public void startNewRound(){
        //TODO
        //this method will check if there is a tie, how many players are left, etc.
    }

    public void endFestival(){
        //need to make a command? to end the festival.
        //this will be called when there is only one person left,
        // when there is a tie and the users want to end the festival,
        // when no one has cards left and they have to end the festival (don't want to ask if the user wants to end)
    }
}