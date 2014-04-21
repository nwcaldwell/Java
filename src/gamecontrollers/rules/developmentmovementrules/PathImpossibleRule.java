package gamecontrollers.rules.developmentmovementrules;


import gamecontrollers.commandcreator.DeveloperCommandCreator;
import gamecontrollers.commandcreator.DeveloperMovementCommandCreator;

import gamecontrollers.Message;


/**
 * Created by kevinnieman on 4/14/14.
 */
public class PathImpossibleRule extends DeveloperMovementRule {
    //checks that the developer can move to an indicated space

    private DeveloperCommandCreator controller;
    private Message message;


    PathImpossibleRule(DeveloperCommandCreator controller){
        this.controller = controller;
    }

    public void update(){
        if(controller.getPath().size() == 0) {
            message = new Message("Target unreachable for selected developer.", true);
            setValidity(false);
        }
        else {
            message = new Message("Jorge is the Path.", false);
            setValidity(true);
        }
    }

    public Message getErrorMessage(){
        return message;
    }


}
