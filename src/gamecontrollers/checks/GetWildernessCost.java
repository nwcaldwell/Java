package gamecontrollers.checks;

import models.board.Space;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class GetWildernessCost {

    public int checkCost(Space space){


        if(space.isInHighlands()){
            return 2;
        }
        else if(space.isInLowlands()){
            return 1;
        }
        else
            throw new IllegalArgumentException();
    }
}
