package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import models.board.SharedResources;
import models.palacefestival.JavaPlayer;

public class NormalTurn extends TurnState{
    private TurnController turnController;
    private TurnState otherState;
    private SharedResources resources;

    //Constants set for this turn
    private int maxCardsDrawn = 2;
    private int maxExtraActionTokensPlayed = 1;
    private int defaultActionPoints = 6;
    private int minTilesPlaced = 1;
    private int minTilesForThisState = 1;

    /*
   ========================================================================
      CONSTRUCTORS
   ========================================================================
    */
    public NormalTurn(TurnController tc, TurnState os, SharedResources sr){
        setActionPoints(defaultActionPoints);

        otherState = os;
        turnController = tc;
        resources = sr;


    }


    /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

    public void playTile(){
        tilePlaced();
        updateState();
    }

    public void removeTile(){
        tileRemoved();
        updateState();
    }

    public void playExtraActionToken(){
        extraActionTokenUsed();
        updateState();
    }

    public void returnExtraActionToken(){
        extraActionTokenUsed();
        updateState();
    }

    public void drawCard(){
        cardDrawn();
        updateState();
    }

    public void returnCard(){
        cardPutBack();
        updateState();
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

    public EndTurnCommand endTurn(){
        return new EndTurnCommand(turnController.getCurrentPlayer());
    }

    /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */

    private void updateState(){
        updateCanEndTurn();
        updateControllerState();
    }

    private void updateCanEndTurn(){
        setCanEndTurn(getNumTilesPlaced() >= minTilesPlaced);
    }

    private void updateControllerState(){
        if(resources.getNumIrrigationTiles() < minTilesForThisState){
            turnController.setTurnState(otherState);
        }
    }

}
