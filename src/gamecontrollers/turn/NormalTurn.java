package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;

public class NormalTurn extends TurnState{
    private TurnController turnController;
    private TurnState otherState;

    //Constants set for this turn
    private int maxCardsDrawn = 2;
    private int maxExtraActionTokensPlayed = 1;
    private int defaultActionPoints = 6;
    private int minTilesPlaced = 1;

    /*
   ========================================================================
      CONSTRUCTORS
   ========================================================================
    */
    public NormalTurn(){
        setActionPoints(defaultActionPoints);
    }


    /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

    public void playTile(){
        tilePlaced();
        updateCanEndTurn();
    }

    public void removeTile(){
        tileRemoved();
        updateCanEndTurn();
    }

    public void playExtraActionToken(){
        extraActionTokenUsed();
        updateCanEndTurn();
    }

    public void returnExtraActionToken(){
        extraActionTokenUsed();
        updateCanEndTurn();
    }

    public void drawCard(){
        cardDrawn();
        updateCanEndTurn();
    }

    public void returnCard(){
        cardPutBack();
        updateCanEndTurn();
    }

    public boolean canDrawCard(){
        return getNumCardsDrawn() < maxCardsDrawn;
    }
    public boolean canPlayExtraActionToken(){
        return getNumExtraActionTokensUsed() < maxExtraActionTokensPlayed;
    }

    public void clear(){
            setActionPoints(defaultActionPoints);
            setCanEndTurn(false);
            clearCounters();
    }

    /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */

    private void updateCanEndTurn(){
        setCanEndTurn(getNumTilesPlaced() >= minTilesPlaced);
    }

}
