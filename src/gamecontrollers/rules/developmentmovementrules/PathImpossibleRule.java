package gamecontrollers.rules.developmentmovementrules;

import gamecontrollers.BoardLogicController;
import gamecontrollers.commandcreator.DevMoveController;

/**
 * Created by kevinnieman on 4/14/14.
 */
public class PathImpossibleRule extends DeveloperMovementRule {
    //checks that the developer can move to an indicated space

    private DevMoveController controller;

    PathImpossibleRule(BoardLogicController board, DevMoveController controller){

    }

    public void update(){

    }

    public String getErrorMessage(){
        return null;
    }
}
