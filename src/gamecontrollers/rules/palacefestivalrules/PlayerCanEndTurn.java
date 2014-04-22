package gamecontrollers.rules.palacefestivalrules;

import gamecontrollers.Message;
import gamecontrollers.rules.Rule;
import models.palacefestival.FestivalModel;

/**
 * Created by ssyyddnneeyy on 4/21/14.
 */
public class PlayerCanEndTurn extends Rule {
    private FestivalModel festivalModel;
    private Message message;

    public PlayerCanEndTurn(FestivalModel model){
        festivalModel = model;
    }

    @Override
    public void update() {
        if(festivalModel.getCurrentPlayer().getBid() >= festivalModel.getHighestBid()) {
            message = new Message("Valid Ending Turn", false);
            setValidity(true);
        }
        else{
            message = new Message("You need to meet or exceed the highest bid. Or drop out", true);
            setValidity(false);
        }

    }

    @Override
    public Message getErrorMessage() {
        return message;
    }
}
