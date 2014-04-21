package gamecontrollers.commandcreator;


import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import models.board.Space;
import models.board.TileComponent;

/**
 * //TODO [Kevin][Jorge]
 */
public abstract class TileCommandCreator implements GameplayCommandCreator{
    public abstract Space getCurrentSpace();

    public abstract TileComponent getCurrentTile();

    public abstract Response checkPossible();

    public abstract int getCost();

    public abstract GameplayActionCommand getCommand();

    public abstract void rotateCurrentTileComponent();


}
