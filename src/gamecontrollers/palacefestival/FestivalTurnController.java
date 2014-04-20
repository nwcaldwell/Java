package gamecontrollers.palacefestival;

import gamecontrollers.Response;
import gamecontrollers.commandcreator.FestivalCommandCreator;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.turn.HistoryChannelController;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;
import java.util.List;

public class FestivalTurnController {
    private FestivalController festivalController;
    private List<FestivalPlayer> turnOrder;
    private FestivalModel festivalModel;
    private FestivalPlayer currentPlayer;

    private FestivalCardController cardController;
    

    private FestivalCommandCreator commandCreator;
    private HistoryChannelController historyChannelController;

	public FestivalTurnController(FestivalController controller, HistoryChannelController hc) {
        festivalController = controller;
        historyChannelController = hc;
	}

    public void startNewFestival(List<FestivalPlayer> players, FestivalModel model){
        turnOrder = players;
        festivalModel = model;
        currentPlayer = turnOrder.get(0);
        cardController = new FestivalCardController(currentPlayer.getCards(), this);
    }

    public void startDrawingCard() {
        commandCreator = cardController;
    }

    public Response commitMove(){
        //check this turns rules stuff real quick
        Response response = commandCreator.checkPossible();

        if(!response.hasErrors()){
            GameplayActionCommand command= commandCreator.getCommand();
            command.execute();
            historyChannelController.addCommand(command);
        }

        return response;
    }

    public void dropOut(){
        // tell the current player to drop out
        currentPlayer.dropOutOfFestival();
        dropCurrentPlayerFromTurnOrder();

        endTurn();
    }

    public void tabThroughPalaceCards(){
        cardController.incrementCurrentCard();
    }

    public void playPalaceCard() {
        startDrawingCard();
        commitMove();
    }

    public void endTurn(){
        // make sure the current player has been marked as "played this round"
        currentPlayer.endTurn();

        // increment current player
        incrementPlayer();

        //check if the round was completed
        startNewRoundCheck();

    }

    private void incrementPlayer(){
        int index = turnOrder.indexOf(currentPlayer);
        currentPlayer = turnOrder.get((index+1) % turnOrder.size());
        cardController.reset(currentPlayer.getCards());
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

        //start new round stuff
    }

    private void dropCurrentPlayerFromTurnOrder(){
        turnOrder.remove(currentPlayer);
    }


    //GETTERS
    public List<FestivalPlayer> getFestivalPlayers(){
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
    }


}