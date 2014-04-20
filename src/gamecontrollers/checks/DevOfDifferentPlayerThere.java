package gamecontrollers.checks;

import gamecontrollers.BoardLogicController;
import models.board.Developer;
import models.board.Space;
import models.palacefestival.JavaPlayer;

import java.util.ArrayList;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class DevOfDifferentPlayerThere {
    private BoardLogicController board;


    public DevOfDifferentPlayerThere(BoardLogicController board){
        this.board = board;
    }


    public boolean check(Space space, JavaPlayer player){
        Developer temp = board.getDeveloperOn(space);
        if(temp.getPlayer() != null && temp.getPlayer() != player)
            return true;
        else
            return false;
    }
}
