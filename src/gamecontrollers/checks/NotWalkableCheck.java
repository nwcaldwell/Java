package gamecontrollers.checks;

import models.board.*;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class NotWalkableCheck {

    public boolean check(Space space){
        return space.getTileComponentContent().getWalkable();
    }
}
