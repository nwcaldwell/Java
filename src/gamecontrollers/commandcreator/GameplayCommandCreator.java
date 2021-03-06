package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.turn.TurnController;

/**
 * //TODO [Kevin][Jorge]
 */
public interface GameplayCommandCreator {

    /*
        This method will return the constructed command
     */
    public GameplayActionCommand getCommand();

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost();

    /*

     */
    public Response checkPossible();

    public abstract void setTurnController(TurnController controller);
}
