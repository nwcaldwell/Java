package gamecontrollers.rules.turnrules;

import gamecontrollers.Message;
import gamecontrollers.turn.TurnController;
import gamecontrollers.turn.TurnState;

/**
 * Created by kevinnieman on 4/14/14.
 */
public class NotEnoughActionPointsRule extends TurnRule {
    TurnController controller;
    private TurnState turn;
    private Message message;

    public NotEnoughActionPointsRule(TurnState turnState, TurnController controller){
        this.turn = turnState;
        this.controller = controller;
    }
    public void update(){
        if(turn.hasEnoughActionPoints(controller.getCost())){
            message = new Message("You can totally do this man", false);
            setValidity(true);
        }
        else{
            message = new Message("Not enough action points", true);
            setValidity(false);
        }

    }
    public Message getErrorMessage(){
        return message;
    }
}
