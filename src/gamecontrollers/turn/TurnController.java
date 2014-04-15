package gamecontrollers.turn;

import gamecontrollers.commandcreator.GameplayCommandCreator;
import models.palacefestival.JavaPlayer;
/**
 * Created by jorgep on 4/14/14.
 */
public class TurnController {
    private GameplayCommandCreator currentCommandCreator;
    private TurnState turnState;
    private JavaPlayer currentPlayer;

    public TurnController(){

    }

    public int getActionPointsLeft(){

        return 0;
    }

    public JavaPlayer getCurrentPlayer(){
        return new JavaPlayer();
    }

    public void commitMove(){}

    public void endTurn(){

    }

    public boolean canEndTurn(){

        return false;
    }

    public void setTurnState(TurnState ts){}

}
