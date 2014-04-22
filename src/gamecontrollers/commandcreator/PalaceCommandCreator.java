package gamecontrollers.commandcreator;


import java.util.List;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import models.board.Direction;
import models.board.Space;


/**
 * Created by kevinnieman on 4/20/14.
 */
public abstract class PalaceCommandCreator implements GameplayCommandCreator {

    public abstract void move(Direction direction);
    public abstract Space getSpace();

    public abstract int getCurrentLevel();

    public abstract int getCitySize();


    public abstract void tabThroughPalacesRemaining();
	public abstract void setCurrentSpace(Space root);
	public abstract void setCurrentLevel(int level);
	public abstract List<Direction> getPath();

}

