package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.commands.gameplaycommands.DevMoveCommand;
import models.board.Developer;
import models.board.Direction;
import models.board.Space;

public class DevMoveController extends DeveloperCommandCreator {
    private Space desiredSpace;
    private Developer currentDeveloper;

    public DevMoveController(){

    }

    public Space getDesiredSpace() {
		return desiredSpace;
	}

	/*
      This method will return the constructed command
   */
    public DevMoveCommand getCommand(){

        throw new UnsupportedOperationException();
    }

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost(){
        return 0;
    }
    
    public Response checkPossible(){
        return null;
    }

    public void setCurrentDeveloper(){

    }

    public void iterateThroughBoardDevelopers(){

    }

    public void move(Direction direction){

    }
}
