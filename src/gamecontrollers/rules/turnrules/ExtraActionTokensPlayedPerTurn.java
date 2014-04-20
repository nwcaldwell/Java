package gamecontrollers.rules.turnrules;

import gamecontrollers.Message;
import gamecontrollers.turn.TurnState;

/**
 * Created by kevinnieman on 4/19/14.
 */
public class ExtraActionTokensPlayedPerTurn extends TurnRule {
    private TurnState turn;
    private Message message;

    public ExtraActionTokensPlayedPerTurn(TurnState turn){
        this.turn = turn;
    }

    public void update(){
        if(turn.getNumExtraActionTokensUsed() >= turn.getMaxExtraActionTokensPerTurn()){
            message = new Message("Cant play anymore extra action tokens this turn", true);
            setValidity(false);
        }
        else{
            message = new Message("All good here", false);
            setValidity(true);
        }
    }

    public Message getErrorMessage(){
        return message;
    }
}
