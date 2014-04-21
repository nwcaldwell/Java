package gamecontrollers.palacefestival;

import gamecontrollers.Response;
import gamecontrollers.commandcreator.FestivalCommandCreator;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.DropOutOfFestivalCommand;
import gamecontrollers.commands.gameplaycommands.EndFestivalCommand;
import gamecontrollers.commands.gameplaycommands.EndFestivalTurnCommand;
import gamecontrollers.rules.PalaceFestivalRules.PlayerCanEndTurn;
import gamecontrollers.rules.Rule;
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
    private List<Rule> rules;

	public FestivalTurnController(
            FestivalController controller,
            HistoryChannelController hc) {
        festivalController = controller;
        historyChannelController = hc;
        rules = new ArrayList<Rule>();
        rules.add(new PlayerCanEndTurn(this));
	}

    /*
    ========================================================================
      Initializing Festival Method
    ========================================================================
    */

    public void startNewFestival(FestivalModel model){
        festivalModel = model;
        turnOrder = festivalModel.getTurnOrder();
        currentPlayer = turnOrder.get(0);
        cardController = new FestivalCardController(currentPlayer.getCards(), this);
    }

    /*
    ========================================================================
      Command Execution Methods
    ========================================================================
    */

    private void executeCommand(GameplayActionCommand command){
        command.execute();
        historyChannelController.addCommand(command);
    }

    public Response commitMove(){
        //check this turns rules stuff real quick
        Response response = commandCreator.checkPossible();

        if(!response.hasErrors()){
            executeCommand(commandCreator.getCommand());
        }

        return response;
    }

    /*
    ========================================================================
      Play Festival Card Methods
    ========================================================================
    */

    public void playPalaceCard() {
        startDrawingCard();
        commitMove();
    }

    public void tabThroughPalaceCards(){
        cardController.incrementCurrentCard();
    }

    private void startDrawingCard() {
        commandCreator = cardController;
    }

    /*
    ========================================================================
      Dropping out methods
    ========================================================================
    */

    public void dropOutCommandCreator(){
        executeCommand(new DropOutOfFestivalCommand(this, currentPlayer, getCurrentPlayerIndex()));
    }

    public void dropOut(){
        currentPlayer.dropOutOfFestival();
        dropCurrentPlayerFromTurnOrder();
        endTurn();
    }

    /*
    ========================================================================
      Ending turn checking
    ========================================================================
    */

    public Response endTurn(){

        Response response = new Response();

        // TODO make this in a rule logicController.checkIfCanEndTurn(currentPlayer.getBid(), festivalModel.getHighestBid()
        for(Rule r:rules){
            if( !checkValidity(r)) {
                response.addMessage(r.getErrorMessage());
            }
        }

        if(!response.hasErrors()) {
            executeCommand(new EndFestivalTurnCommand(this, currentPlayer, getCurrentPlayerIndex()));
        }

        return response;
    }

    private boolean checkValidity(Rule rule){
        rule.update();
        return rule.getValidity();
    }

    public void endTurnFinalization(){
        // make sure the current player has been marked as "played this round"
        currentPlayer.endTurn();

        // increment current player
        incrementPlayer();

        //check if the round was completed
        if ( startNewRoundCheck() ) {
            startNewRound();
        }
    }

    /*
    ========================================================================
      End Turn backend
    ========================================================================
    */

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

    /*
    ========================================================================
      New Round Methods
    ========================================================================
    */

    private boolean startNewRoundCheck(){
        //if the player has played this round is true, that means that we need to start a new round
        return currentPlayer.hasPlayedThisRound();
    }

    private void startNewRound(){
        for(FestivalPlayer player : turnOrder){
            player.startNewRound();
        }

        if(!festivalModel.canStartNewRound()){
            executeCommand(new EndFestivalCommand(festivalModel, this));
        }
    }

    /*
    ========================================================================
      Getters
    ========================================================================
    */

    public FestivalModel getFestivalModel() {
        return festivalModel;
    }

    public FestivalPlayer getCurrentPlayer(){
        return currentPlayer;
    }

    public List<FestivalPlayer> getTurnOrder(){
        return turnOrder;
    }

    public PalaceCard getFestivalCard(){
        return festivalModel.getFestivalCard();
    }

    public int getHighestBid(){
        return festivalModel.getHighestBid();
    }

    public int getCurrentPlayerIndex(){
        return turnOrder.indexOf(currentPlayer);
    }

    public int getSelectedCard(){
        return cardController.getIndexOfCurrentCard();
    }

    /*
    ========================================================================
      Setters
    ========================================================================
    */

    public void setTurnOrder(List<FestivalPlayer> players){
        turnOrder = players;
    }

    public void setCurrentPlayer(FestivalPlayer currentPlayer){
        this.currentPlayer = currentPlayer;
        resetCardControllerCards();
    }

    public void setFestivalModel(FestivalModel model){
        this.festivalModel = model;
    }

    /*
    ========================================================================
      Undo Methods
    ========================================================================
    */

    public void undoEndTurn(FestivalPlayer player, int playerIndex){
        player.undoEndTurn();
        addPlayerBackToTurnOrder(player, playerIndex);
    }

    public void undoEndFestival(FestivalModel model){
        setFestivalModel(model);
        setTurnOrder(model.getPlayers());
        setCurrentPlayer(turnOrder.get(0));
    }

    public void undoDropOut(FestivalPlayer player, int index){
        player.undoDropOutOfFestival();
        addPlayerBackToTurnOrder(player, index);
    }
}