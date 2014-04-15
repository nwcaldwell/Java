package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import view.commands.gameplaycommands.MoveDeveloperCommand;

/**
 * Created by jorgep on 4/14/14.
 */
public class MoveDeveloperController extends DeveloperCommandCreator {

    /*
      This method will return the constructed command
   */
    public MoveDeveloperCommand getCommand(){

        throw new UnsupportedOperationException();
    }

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost(){
        return 0;
    }

    /*

     */
    public Response checkPossible(){
        return null;
    }
}
