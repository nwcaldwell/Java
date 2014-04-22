package gamecontrollers.turn;

import gamecontrollers.BoardLogicController;
import gamecontrollers.commands.gameplaycommands.EndFinalTurnCommand;
import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import gamecontrollers.rules.turnrules.CardsDrawnPerTurnRule;
import gamecontrollers.rules.turnrules.ExtraActionTokensPlayedPerTurn;
import gamecontrollers.rules.turnrules.NotEnoughActionPointsRule;
import gamecontrollers.rules.turnrules.TurnRule;
import models.board.SharedResources;
import models.palacefestival.JavaPlayer;

import java.util.ArrayList;

public class LastTurn extends TurnState {

    private JavaPlayer firstLastTurn;
    private JavaPlayer currentPlayer;
    private FinalScoreCalculator scoreCalculator;
    private TurnController turnController;
    private SharedResources resources;

    //Constants set for this turn
    private int maxCardsDrawn = 2;
    private int maxExtraActionTokensPlayed = 1;
    private int defaultActionPoints = 6;
    private int maxTilesForThisState = 0;
    private int minTilesForThisState = 0;

    //My own private rules? you shouldnt have
    private CardsDrawnPerTurnRule cardRule;
    private NotEnoughActionPointsRule actionPointsRule;
    private ExtraActionTokensPlayedPerTurn extraTokens;


    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */
    public LastTurn(TurnController tc ){
        setActionPoints(defaultActionPoints);
        //no requirements to end turn on last turn
        setCanEndTurn(true);

        //set stuff
        firstLastTurn = tc.getCurrentPlayer();
        scoreCalculator = new FinalScoreCalculator(tc.getCurrentPlayer(), tc.getBoardLogicController());
        turnController = tc;
        resources = tc.getSharedResources();


        setMaxCardsPerTurn(maxCardsDrawn);
        setMaxExtraActionTokensPerTurn(maxExtraActionTokensPlayed);
        setMinTilesPlacePerTurn(minTilesForThisState);

        //instantiate rules and set them in rules list
        cardRule = new CardsDrawnPerTurnRule(this);
        actionPointsRule = new NotEnoughActionPointsRule(this, turnController);
        extraTokens = new ExtraActionTokensPlayedPerTurn(this);

        //set rules list
        addRules(cardRule, actionPointsRule, extraTokens);
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
        extraActionTokenPutBack();
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


    public void clear(){
        setActionPoints(defaultActionPoints);
        setCanEndTurn(false);
        clearCounters();
    }

    //TODO end game checks and stuff

    public EndFinalTurnCommand endTurn(){
        return new EndFinalTurnCommand(scoreCalculator.calculateScore(), turnController.getCurrentPlayer());
    }

    /*
  ========================================================================
     RULE CHECKING METHODS
  ========================================================================
   */

    public boolean canDrawCard(){
        return cardRule.getValidity();
    }

    public boolean canPlayExtraActionToken(){
        return extraTokens.getValidity();
    }

    public boolean hasEnoughActionPoints(int i){
        return actionPointsRule.getValidity() && canEndTurn();
    }

    /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */



    private void updateState(){
        notifyRules();
        updateControllerState();
    }

    private void updateControllerState(){
        if(resources.getNumThreeTiles() > maxTilesForThisState){
            turnController.setTurnState(new NormalTurn(turnController));
        }
    }


}
