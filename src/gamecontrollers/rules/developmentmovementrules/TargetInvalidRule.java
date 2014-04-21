package gamecontrollers.rules.developmentmovementrules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.DeveloperCommandCreator;
import gamecontrollers.commandcreator.DeveloperMovementCommandCreator;
import models.board.Board;

/**
 * Created by kevinnieman on 4/14/14.
 */
public class TargetInvalidRule extends DeveloperMovementRule {
    private DeveloperCommandCreator controller;
    private Board board;
    private Message message;

    TargetInvalidRule(DeveloperCommandCreator controller, Board board, Message message){
        this.controller = controller;
        this.board = board;
        this.message = message;
    }

    public void update(){
        if(board.hasDeveloperOn(controller.getDesiredSpace())) {
            message = new Message("Developer on space already", true);
            setValidity(false);
        }
        else{
            message = new Message("Space is fine", false);
            setValidity(true);
        }
    }

    public Message getErrorMessage(){
        return null;
    }
}
