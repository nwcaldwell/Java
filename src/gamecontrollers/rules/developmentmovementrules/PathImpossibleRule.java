package gamecontrollers.rules.developmentmovementrules;


import gamecontrollers.BoardLogicController;
import gamecontrollers.Response;
import gamecontrollers.commandcreator.DevMoveController;

import gamecontrollers.Message;
import gamecontrollers.rules.Rule;


/**
 * Created by kevinnieman on 4/14/14.
 */
public class PathImpossibleRule extends DeveloperMovementRule {
    //checks that the developer can move to an indicated space

    private DevMoveController controller;
    private Message message;


    PathImpossibleRule(DevMoveController controller){
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
