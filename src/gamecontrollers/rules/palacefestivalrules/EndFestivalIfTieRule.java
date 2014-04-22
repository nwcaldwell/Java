package gamecontrollers.rules.palacefestivalrules;

import gamecontrollers.Message;
import gamecontrollers.rules.Rule;
import models.palacefestival.FestivalModel;

/**
 * Created by ssyyddnneeyy on 4/22/14.
 */
public class EndFestivalIfTieRule extends Rule {
    private FestivalModel festivalModel;
    private Message message;

    public EndFestivalIfTieRule(FestivalModel model){
        festivalModel = model;
    }

    @Override
    public void update() {
        if(!festivalModel.checkForTie()) {
            message = new Message("No tie, continue", false);
            setValidity(true);
        }
        else{
            message = new Message("Ending festival from tie", true);
            setValidity(false);
        }

    }

    @Override
    public Message getErrorMessage() {
        return message;
    }
}
