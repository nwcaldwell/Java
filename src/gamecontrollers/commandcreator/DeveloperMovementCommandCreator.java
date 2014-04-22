package gamecontrollers.commandcreator;

import gamecontrollers.BoardLogicController;
import gamecontrollers.Message;
import gamecontrollers.Response;
import gamecontrollers.commands.gameplaycommands.DevMoveCommand;
import gamecontrollers.rules.Rule;
import gamecontrollers.rules.developmentmovementrules.DeveloperMovementRule;
import gamecontrollers.turn.TurnController;
import models.board.Developer;
import models.board.Direction;
import models.board.Space;

import java.util.ArrayList;
import java.util.List;

public class DeveloperMovementCommandCreator extends DeveloperCommandCreator {
    private Space desiredSpace;
    private Developer currentDeveloper;
    private List<Space> path = new ArrayList<Space>();
    private int cost;
    private ArrayList<DeveloperMovementRule> rules = new ArrayList<DeveloperMovementRule>();
    private BoardLogicController logicController;
    private TurnController turnController;

    /*
  ========================================================================
       CONSTRUCTORS
  ========================================================================
   */

    public DeveloperMovementCommandCreator(TurnController controller, BoardLogicController logicController){
        this.turnController = controller;
        this.logicController = logicController;
    }


    /*
   ========================================================================
      GETTERS AND SETTERS
   ========================================================================
    */

    public void setTurnController(TurnController controller){
        this.turnController = controller;
    }
    public Space getDesiredSpace() {
		return desiredSpace;
	}

	/*
      This method will return the constructed command
   */
    public DevMoveCommand getCommand(){

        return new DevMoveCommand(currentDeveloper, currentDeveloper.getSpace(), desiredSpace, turnController);
    }

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost(){
        return cost;
    }

    public List<Space> getPath(){
        return path;
    }

    public Response setCurrentDeveloper(){
        Response response = new Response();

        //They want to place a nonexisting developer
        //check that they can play a developer
        if(turnController.getCurrentPlayer().getNumDevelopers() >= 0) {
            //create the developer
            currentDeveloper = new Developer(turnController.getCurrentPlayer());
        }
        else{
            response = new Response(new Message("No Developers left", true));
        }

        //update state
        updateState();

        //return the response
        return response;
    }



   /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

    public void iterateThroughBoardDevelopers(){
        //iterate through the list of developers on the board
        currentDeveloper = logicController.getNextDeveloper(currentDeveloper);

        //update state
        updateState();
    }

    public void move(Direction direction){
        desiredSpace = desiredSpace.getAdjacentSpace(direction);
        //update state
        updateState();
    }

    public Response checkPossible() {
        Response response = new Response();

        for (Rule rool : rules) {
            response.addMessage(rool.getErrorMessage());
        }
        return response;
    }

   /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */


    private void updateState(){
        //update the cost and path of the controller
	    //TODO: Will needs to talk to Kevin about this
        cost = logicController.moveDeveloperWithinCentralJava(turnController.getCurrentPlayer(), currentDeveloper.getSpace(), desiredSpace, path);
        notifyRules();
    }

    private void notifyRules(){
        for(Rule rool : rules){
            rool.update();
        }
    }
}
