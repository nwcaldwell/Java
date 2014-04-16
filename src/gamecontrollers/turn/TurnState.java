package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;

public abstract class TurnState {
    private int actionPoints;
    //canEndTurn defaults to false and must be set to true
    private boolean canEndTurn = false;
    private int numTilesPlaced;
    private int numCardsDrawn;
    private int numExtraActionTokensUsed;

    /*
   ========================================================================
       PUBLIC DEFAULT METHODS
   ========================================================================
    */

    //return number of action points
    //This takes into account if you can end your turn
    //return false unless canEndTurn has been set to true
    public boolean hasEnoughActionPoints( int i ){
        return actionPoints <= i && canEndTurn;
    }

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
    public EndTurnCommand endTurn(){
        // TODO implement
        return null;
    }


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

    protected void clearCounters() {
        numTilesPlaced = 0;
        numCardsDrawn = 0;
        numExtraActionTokensUsed = 0;
    }

     /*
    ========================================================================
        PROTECTED GET METHODS
    ========================================================================
     */

    protected int getNumExtraActionTokensUsed(){
        return numExtraActionTokensUsed;
    }

    protected int getActionPoints(){
        return actionPoints;
    }

    protected int getNumTilesPlaced(){
        return numTilesPlaced;
    }

    protected int getNumCardsDrawn(){
        return numCardsDrawn;
    }
}
