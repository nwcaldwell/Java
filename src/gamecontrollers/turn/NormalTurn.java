package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import gamecontrollers.rules.turnrules.CardsDrawnPerTurnRule;
import gamecontrollers.rules.turnrules.ExtraActionTokensPlayedPerTurn;
import gamecontrollers.rules.turnrules.NotEnoughActionPointsRule;
import gamecontrollers.rules.turnrules.TurnRule;
import models.board.SharedResources;
import models.palacefestival.JavaPlayer;

import java.util.ArrayList;

public class NormalTurn extends TurnState{
    private TurnController turnController;
    private SharedResources resources;

    //Constants set for this turn
    private int maxCardsDrawn = 2;
    private int maxExtraActionTokensPlayed = 1;
    private int defaultActionPoints = 6;
    private int minTilesPlaced = 1;
    private int minTilesForThisState = 1;

    //private rules? i bet you do this for every turnstate
    private CardsDrawnPerTurnRule cardRule;
    private NotEnoughActionPointsRule actionPointsRule;
    private ExtraActionTokensPlayedPerTurn extraTokens;

    /*
   ========================================================================
      CONSTRUCTORS
   ========================================================================
    */
    public NormalTurn(TurnController tc){
        setActionPoints(defaultActionPoints);

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
        notifyRules();
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

    public EndTurnCommand endTurn(){
        return new EndTurnCommand(turnController);
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
        updateCanEndTurn();
        updateControllerState();
    }

    private void updateCanEndTurn(){
        setCanEndTurn(getNumTilesPlaced() >= minTilesPlaced);
    }

    private void updateControllerState(){
        if(resources.getNumIrrigationTiles() < minTilesForThisState){
            turnController.setTurnState(new LastTurn(turnController));
        }
    }

}
