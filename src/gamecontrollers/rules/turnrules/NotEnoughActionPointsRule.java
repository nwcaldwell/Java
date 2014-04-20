package gamecontrollers.rules.turnrules;

import gamecontrollers.Message;
import gamecontrollers.turn.TurnController;

/**
 * Created by kevinnieman on 4/14/14.
 */
public class NotEnoughActionPointsRule extends TurnRule {
    private TurnController controller;
    private Message message;

    NotEnoughActionPointsRule(TurnController controller){
        this.controller = controller;
    }
    public void update(){
        if(controller.hasEnoughActionPoints(controller.getCost())){
            message = new Message("You can totally do this man", false);
            setValidity(true);
        }
        else{
            message = new Message("Not enough action points", true);
            setValidity(false);
        }

    }
    public Message getErrorMessage(){
        return null;
    }
}
