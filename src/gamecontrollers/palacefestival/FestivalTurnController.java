package gamecontrollers.palacefestival;

import gamecontrollers.Response;
import gamecontrollers.commandcreator.FestivalCommandCreator;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.DropOutOfFestivalCommand;
import gamecontrollers.commands.gameplaycommands.EndFestivalCommand;
import gamecontrollers.commands.gameplaycommands.EndFestivalTurnCommand;
import gamecontrollers.turn.HistoryChannelController;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;

import java.util.List;

public class FestivalTurnController {
    private FestivalController festivalController;
    private FestivalLogicController logicController;
    private List<FestivalPlayer> turnOrder;
    private FestivalModel festivalModel;
    private FestivalPlayer currentPlayer;

    private FestivalCardController cardController;

    private FestivalCommandCreator commandCreator;
    private HistoryChannelController historyChannelController;

	public FestivalTurnController(FestivalController controller, FestivalLogicController logic, HistoryChannelController hc) {
        festivalController = controller;
        logicController = logic;
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
            executeCommand(commandCreator.getCommand());
        }

        return response;
    }

    private void executeCommand(GameplayActionCommand command){
        command.execute();
        historyChannelController.addCommand(command);
    }

    //DROPPING OUT OF FESTIVAL METHODS

    public void dropOutCommandCreator(){
        executeCommand(new DropOutOfFestivalCommand(this, currentPlayer, getCurrentPlayerIndex()));
    }

    public void dropOut(){
        currentPlayer.dropOutOfFestival();
        dropCurrentPlayerFromTurnOrder();
        endTurn();
    }

    public void undoDropOut(FestivalPlayer player, int index){
        player.undoDropOutOfFestival();
        addPlayerBackToTurnOrder(player, index);
    }

    //TABBING THOUGH PALACE CARD METHOD

    public void tabThroughPalaceCards(){
        cardController.incrementCurrentCard();
    }

    //PLAYING PALACE CARD METHOD

    public void playPalaceCard() {
        startDrawingCard();
        commitMove();
    }

    //ENDING TURN METHODS

    public void endTurnCommandCreator(){
        executeCommand(new EndFestivalTurnCommand(this, currentPlayer, getCurrentPlayerIndex()));
    }

    public void endTurn(){
        // make sure the current player has been marked as "played this round"
        currentPlayer.endTurn();

        // increment current player
        incrementPlayer();

        //check if the round was completed
        startNewRoundCheck();

    }

    public void undoEndTurn(FestivalPlayer player, int playerIndex){
        player.undoEndTurn();
        addPlayerBackToTurnOrder(player, playerIndex);
    }

    //PRIVATE HELPER METHODS

    private void dropCurrentPlayerFromTurnOrder(){
        turnOrder.remove(currentPlayer);
    }

    private void incrementPlayer(){
        int index = turnOrder.indexOf(currentPlayer);
        setCurrentPlayer(turnOrder.get((index+1) % turnOrder.size()));
        resetCardControllerCards();
    }

    private void addPlayerBackToTurnOrder(FestivalPlayer player, int playerIndex){
        turnOrder.add(playerIndex, player);
        setCurrentPlayer(turnOrder.get(playerIndex));
        resetCardControllerCards();
    }

    private void resetCardControllerCards(){
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
//TODO check to make sure this isnt violating TDA
        //start new round stuff
        //checks for ending festival, ties, etc
        if(festivalModel.onePlayerLeft() || festivalModel.playersAreOutOfCards()){
            executeCommand(new EndFestivalCommand(festivalModel, logicController.computePointsWon(festivalModel.getWinners().size(), festivalModel.getPalaceValue())));
        }
        else if(festivalModel.isTie()){
            //ask if the users would like to end the festival
            //TODO
        }
    }

    //GETTERS
    public FestivalPlayer getCurrentPlayer(){
        return currentPlayer;
    }

    public FestivalModel getFestivalModel(){
        return festivalModel;
    }

    public int getCurrentPlayerIndex(){
        return turnOrder.indexOf(currentPlayer);
    }

    //SETTERS
    public void setCurrentPlayer(FestivalPlayer currentPlayer){
        this.currentPlayer = currentPlayer;
    }


}