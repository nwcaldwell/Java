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

        ArrayList<FestivalPlayer> festivalPlayers = new ArrayList<FestivalPlayer>();

        for(int i = 0; i < players.length; i++){
            if(logicController.canParticipateInFestival(players[i].getPalaceCards(), festivalCard)){
                //they can participate in the festival
                festivalPlayers.add(new FestivalPlayer(players[i], logicController.getEligibleCards(players[i].getPalaceCards(), festivalCard)));
            }
        }

        turnController.startNewFestival(festivalPlayers);
        festivalModel = new FestivalModel(festivalPlayers, festivalCard, palaceValue);
    }

    public void playCard(){
        //remove the card from the player's stash, and put it into the discarded pile
        //add the card to the deck's graveyard
        //increment the current player's bid
        //check if the bid is higher than the highest bid in the model, if so update it

        //need the festival card for comparison
    }

    public void dropOutOfFestival(){
        turnController.dropCurrentPlayer();
    }

    public void undoDropOutOfFestival(FestivalPlayer player, int index) {
        turnController.undoDropOutOfFestival(player, index);
    }

    public void endTurn(){
        //check if the user is allowed to end the turn
        //if so then end turn
        //if not, force to drop out
    }

    public void startNewRound(){
        //TODO
        //this method will check if there is a tie, how many players are left, etc.
        logicController.checkIfCanEndGame();
        //somehow we need to talk to the view to ask the user if they want to end the game after a tie.
    }

    //TODO is this necessary?
    public void endFestival(FestivalPlayer winner){
        //need to make a command? to end the festival.
        //this will be called when there is only one person left,
    }

    public void endFestival(ArrayList<FestivalPlayer> winners){
        // when there is a tie and the users want to end the festival,
        // when no one has cards left and they have to end the festival (don't want to ask if the user wants to end)
    }

}