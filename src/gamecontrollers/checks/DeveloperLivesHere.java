package gamecontrollers.checks;

import gamecontrollers.BoardLogicController;
import models.board.Space;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class DeveloperLivesHere {
    private BoardLogicController controller;

    public DeveloperLivesHere(BoardLogicController controller){
        this.controller = controller;
    }

    public boolean check(Space space){
        return controller.hasDeveloperOn(space);
    }
}
