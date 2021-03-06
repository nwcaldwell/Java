package gamecontrollers.commandcreator;

import gamecontrollers.commands.GameplayActionCommand;
import models.board.Direction;
import models.board.Space;

import java.util.List;

/**
 * //TODO [Kevin][Jorge]
 */
public abstract class DeveloperCommandCreator implements GameplayCommandCreator{

    public abstract GameplayActionCommand getCommand();

    public abstract void move(Direction direction);

    public abstract int getCost();

    public abstract List<Space> getPath();

    public abstract Space getDesiredSpace();

}
