package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import models.palacefestival.JavaPlayer;
public class LastTurn extends TurnState {

    private JavaPlayer firstLastTurn;
    private FinalScoreCalculator scoreCalculator;
    private TurnController turnController;
    private TurnState otherState;

    //Constants set for this turn
    private int maxCardsDrawn = 2;
    private int maxExtraActionTokensPlayed = 1;
    private int defaultActionPoints = 6;


    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */
    public LastTurn(){
        setActionPoints(6);
        //no requirements to end turn on last turn
        setCanEndTurn(true);
    }



    /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

    public void playTile(){
        tilePlaced();
    }

    public void removeTile(){
        tileRemoved();
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


}
