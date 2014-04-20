package gamecontrollers.checks;

import models.board.Space;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class UnfertilizedLand {

    public boolean check(Space space){
        return space.getHeight() == 0;
    }
}
