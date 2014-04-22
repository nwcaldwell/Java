package gamecontrollers.rules.turnrules;

import gamecontrollers.Message;
import gamecontrollers.turn.TurnState;

/**
 * Created by kevinnieman on 4/19/14.
 */
public class CardsDrawnPerTurnRule extends TurnRule{

    private TurnState turn;
    private Message message;

    public CardsDrawnPerTurnRule(TurnState turn){
        this.turn = turn;
    }

    public void update(){
        if(turn.getNumCardsDrawn() >= turn.getMaxCardsPerTurn()){
            message = new Message("Cant draw anymore cards this turn", true);
            setValidity(false);
        }
        else{
            message = new Message("No problemo", false);
            setValidity(true);
        }
    }

    public Message getErrorMessage(){
        return message;
    }

}
