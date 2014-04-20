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

public class DevMoveController extends DeveloperCommandCreator {
    private Space desiredSpace;
    private Space originalSpace;
    private Developer currentDeveloper;
    private List<Space> path;
    private int cost;
    private ArrayList<DeveloperMovementRule> rules;
    private BoardLogicController logicController;
    private TurnController turnController;

    /*
  ========================================================================
       CONSTRUCTORS
  ========================================================================
   */
    public DevMoveController(){

    }


    /*
   ========================================================================
      GETTERS AND SETTERS
   ========================================================================
    */
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
        return cost;
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

        //notify rules
        notifyRules();

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

        //notify rules
        notifyRules();
    }

    public void move(Direction direction){

        //notify rules
        notifyRules();
    }

    public Response checkPossible(){
        Response response = new Response();

        for(Rule rool : rules) {
            response.addMessage(rool.getErrorMessage());
        }
        return response;
    }

   /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */

    private void notifyRules(){
        for(Rule rool : rules){
            rool.update();
        }
    }
}
