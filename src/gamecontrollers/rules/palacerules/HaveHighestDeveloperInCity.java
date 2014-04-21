package gamecontrollers.rules.palacerules;

import gamecontrollers.BoardLogicController;
import gamecontrollers.Message;
import gamecontrollers.commandcreator.PalaceCommandCreator;
import gamecontrollers.turn.TurnController;
import models.board.TileComponentContent;

import java.util.List;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class HaveHighestDeveloperInCity extends PalaceRule {
    private PalaceCommandCreator creator;
    private TurnController controller;
    private Message message;
    private List<TileComponentContent> city;
    private BoardLogicController board;

    public HaveHighestDeveloperInCity(PalaceCommandCreator creator, TurnController controller, List<TileComponentContent> city, BoardLogicController board){
        this.creator = creator;
        this.controller = controller;
        this.city = city;
        this.board = board;
    }
    @Override
    public void update() {
        if(board.findHighestDeveloper(creator.getSpace(), controller.getCurrentPlayer())){
            message = new Message("You can do it", false);
            setValidity(true);
        }
        else{
            message = new Message("You're developer is not the highest", true);
            setValidity(false);
        }
    }

    @Override
    public Message getErrorMessage() {
        return message;
    }
}
