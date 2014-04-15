package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import view.commands.Command;

/**
 * //TODO [Kevin][Jorge]
 */
public interface GameplayCommandCreator {

    /*
        This method will return the constructed command
     */
    public Command  getCommand();

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost();

    /*

     */
    public Response checkPossible();
}
