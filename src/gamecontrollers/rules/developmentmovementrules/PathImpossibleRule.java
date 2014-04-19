package gamecontrollers.rules.developmentmovementrules;


import gamecontrollers.BoardLogicController;
import gamecontrollers.commandcreator.DevMoveController;

import gamecontrollers.Message;
import gamecontrollers.rules.Rule;


/**
 * Created by kevinnieman on 4/14/14.
 */
public class PathImpossibleRule extends DeveloperMovementRule {
    //checks that the developer can move to an indicated space

    private DevMoveController controller;



    PathImpossibleRule(DevMoveController controller){
        this.controller = controller;
    }

    public void update(){

    }

    public Message getErrorMessage(){
        return null;
    }


}
