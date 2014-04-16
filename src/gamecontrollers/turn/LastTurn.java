package gamecontrollers.turn;

import gamecontrollers.BoardLogicController;
import gamecontrollers.commands.gameplaycommands.EndFinalTurnCommand;
import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import models.board.SharedResources;
import models.palacefestival.JavaPlayer;
public class LastTurn extends TurnState {

    private JavaPlayer firstLastTurn;
    private JavaPlayer currentPlayer;
    private FinalScoreCalculator scoreCalculator;
    private TurnController turnController;
    private TurnState otherState;
    private SharedResources resources;

    //Constants set for this turn
    private int maxCardsDrawn = 2;
    private int maxExtraActionTokensPlayed = 1;
    private int defaultActionPoints = 6;
    private int maxTilesForThisState = 0;


    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */
    public LastTurn(JavaPlayer p, TurnController tc, TurnState ts,  SharedResources sr, BoardLogicController bl){
        setActionPoints(defaultActionPoints);
        //no requirements to end turn on last turn
        setCanEndTurn(true);

        //set stuff
        firstLastTurn = p;
        scoreCalculator = new FinalScoreCalculator(p, bl);
        turnController = tc;
        resources = sr;
        otherState = ts;
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
    }

    public void returnExtraActionToken(){
        extraActionTokenUsed();
    }

    public void drawCard(){
        cardDrawn();
    }

    public void returnCard(){
        cardPutBack();
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

    public EndFinalTurnCommand endTurn(){
        return new EndFinalTurnCommand(scoreCalculator.calculateScore(), turnController.getCurrentPlayer());
    }

    /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */

    private void updateState(){
        if(resources.getNumThreeTiles() > maxTilesForThisState){
            turnController.setTurnState(otherState);
        }
    }


}
