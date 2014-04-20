package gamecontrollers.palacefestival;

import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;

public class FestivalTurnController {
    private FestivalController festivalController;
    private ArrayList<FestivalPlayer> turnOrder;
    private FestivalPlayer currentPlayer;
    private FestivalCardController cardController;

	public FestivalTurnController(FestivalController controller) {
        festivalController = controller;
	}

    public void startNewFestival(ArrayList<FestivalPlayer> players){
        turnOrder = players;
        currentPlayer = turnOrder.get(0);
        setNewFestivalController();
    }

    public void dropCurrentPlayer(){
        // tell the current player to drop out
        currentPlayer.dropOutOfFestival();

        dropCurrentPlayerFromTurnOrder();
        endTurn();
    }

    public void endTurn(){
        // make sure the current player has been marked as "played this round"
        currentPlayer.endTurn();

        // increment current player
        incrementPlayer();

        //check if the round was completed
        startNewRoundCheck();

    }

    public PalaceCard getSelectedCard(){
        return cardController.getCurrentCard();
    }

    public void tabThroughPalaceCards(){
        cardController.incrementCurrentCard();
    }


    private void incrementPlayer(){
        int index = turnOrder.indexOf(currentPlayer);
        currentPlayer = turnOrder.get((index+1) % turnOrder.size());
    }

    private void startNewRoundCheck(){
        //if the player has played this round is true, that means that we need to start a new round
        if(currentPlayer.hasPlayedThisRound()){
            startNewRound();
        }
    }

    private void startNewRound(){
        for(FestivalPlayer player : turnOrder){
            player.startNewRound();
        }
        festivalController.startNewRound();
    }

    private void dropCurrentPlayerFromTurnOrder(){
        turnOrder.remove(currentPlayer);
    }


    //GETTERS
    public ArrayList<FestivalPlayer> getFestivalPlayers(){
        return turnOrder;
    }

    public FestivalPlayer getCurrentPlayer(){
        return currentPlayer;
    }

    //SETTERS
    public void setTurnOrder(ArrayList<FestivalPlayer> players, FestivalPlayer currentPlayer){
        this.turnOrder = players;
        setCurrentPlayer(currentPlayer);
    }

    public void setCurrentPlayer(FestivalPlayer currentPlayer){
        this.currentPlayer = currentPlayer;
        setNewFestivalController();
    }

    private void setNewFestivalController(){
        this.cardController = new FestivalCardController(currentPlayer.getCards());
    }
}