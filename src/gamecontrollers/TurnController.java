package gamecontrollers;

import models.board.Player;

/**
 * Created by jorgep on 4/14/14.
 */
public class TurnController {
    private CommandCreator currentCommandCreator;
    private TurnState turnState;
    private Player currentPlayer;

    public TurnController(){

    }

    public int getActionPointsLeft(){

        return 0;
    }

    public Player getCurrentPlayer(){
        return new Player();
    }

    public void commitMove(){}

    public void endTurn(){

    }

    public boolean canEndTurn(){

        return false;
    }

    public void setTurnState(TurnState ts){}

}
