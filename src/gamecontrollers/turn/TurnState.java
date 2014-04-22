package gamecontrollers.turn;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import gamecontrollers.rules.Rule;
import gamecontrollers.rules.turnrules.TurnRule;

import java.util.ArrayList;
import java.util.List;

public abstract class TurnState {
    private int actionPoints;
    //canEndTurn defaults to false and must be set to true
    private boolean canEndTurn = false;
    private int numTilesPlaced;
    private int numCardsDrawn;
    private int numExtraActionTokensUsed;
    private int maxCardsDrawn;
    private int minTilesPlaced;
    private int maxExtraActionTokensPlayed;
    //Turn rules
    private List<TurnRule> rules = new ArrayList<TurnRule>();


    /*
   ========================================================================
       PUBLIC DEFAULT METHODS
   ========================================================================
    */




    //increase action points for Extra action tokens or undo
    public void increaseActionPoints( int i ){
        actionPoints += i;
    }

    //decrease action points for actions
    public void decreaseActionPoints( int i ){
        actionPoints -= i;
    }

    //return the boolean if they can end turn
    //defaults to false if not set by subclass
    public boolean canEndTurn(){
        return canEndTurn;
    }


    //create EndTurnCommand and return it
    public abstract GameplayActionCommand endTurn();


     /*
    ========================================================================
        ABSTRACT METHODS
    ========================================================================
     */

    public abstract void playTile();
    public abstract void removeTile();
    public abstract void playExtraActionToken();
    public abstract void returnExtraActionToken();
    public abstract void drawCard();
    public abstract void returnCard();

    public abstract boolean canDrawCard();
    public abstract boolean canPlayExtraActionToken();
    public abstract boolean hasEnoughActionPoints( int i );



    //clears out turn state for next action
    public abstract void clear();



    /*
    ========================================================================
        PROTECTED METHODS
    ========================================================================
     */

    protected void setActionPoints(int i){
        this.actionPoints = i;
    }

    protected void setCanEndTurn(boolean b){
        canEndTurn = b;
    }

    protected void tilePlaced(){
        numTilesPlaced++;
    }

    protected void tileRemoved(){
        numTilesPlaced--;
    }

    protected void cardDrawn(){
        numCardsDrawn++;
    }

    protected void cardPutBack(){
        numCardsDrawn--;
    }

    protected void extraActionTokenUsed(){
        numExtraActionTokensUsed++;
    }

    protected void extraActionTokenPutBack(){
        numExtraActionTokensUsed--;
    }

    protected void setMaxCardsPerTurn(int i){
        this.maxCardsDrawn = i;
    }
    protected void setMaxExtraActionTokensPerTurn(int i){
        this.maxExtraActionTokensPlayed = i;
    }
    protected void setMinTilesPlacePerTurn(int i){
        this.minTilesPlaced = i;
    }

    protected void clearCounters() {
        numTilesPlaced = 0;
        numCardsDrawn = 0;
        numExtraActionTokensUsed = 0;
    }

    /*
        RULE METHODS
     */

    protected void setRules(List<TurnRule> rules){
        this.rules = rules;
    }

    protected void addRules(TurnRule ... rules){
        for(TurnRule rool : rules){
            this.rules.add(rool);
        }
    }

    protected void notifyRules(){
        for(Rule rool : rules){
            rool.update();
        }
    }

    public Response checkRules(){
        Response response = new Response();

        for(Rule rool : rules){
            response.addMessage(rool.getErrorMessage());
        }

        return response;
    }

     /*
    ========================================================================
        PUBLIC GET METHODS
    ========================================================================
     */

    public int getNumExtraActionTokensUsed(){
        return numExtraActionTokensUsed;
    }

    public int getActionPoints(){
        return actionPoints;
    }

    public int getNumTilesPlaced(){
        return numTilesPlaced;
    }

    public int getNumCardsDrawn(){
        return numCardsDrawn;
    }

    public int getMaxCardsPerTurn(){
        return maxCardsDrawn;
    }
    public int getMaxExtraActionTokensPerTurn(){
        return maxExtraActionTokensPlayed;
    }
    public int getMinTilesPlacePerTurn(){
        return minTilesPlaced;
    }
}
