package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.PlaceTileCommand;
import gamecontrollers.rules.Rule;
import gamecontrollers.rules.tileplacementrules.TilePlacementRule;
import gamecontrollers.turn.TurnController;
import models.board.Direction;
import models.board.SharedResources;
import models.board.Space;
import models.board.TileComponent;

import java.util.ArrayList;

public class TilePlacementCommandCreator extends TileCommandCreator {
	private Space currentSpace;
	private TileComponent currentTile;
    private ArrayList<TilePlacementRule> rules;
    private TurnController controller;
    private int cost;
    private TileCreationVisitor visitor;
    private SharedResources resources;

    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */

	public TilePlacementCommandCreator(TurnController controller, SharedResources resources) {
        this.controller = controller;
        visitor = new TileCreationVisitor(controller, resources);
        this.resources = resources;
    }


    /*
   ========================================================================
      GETTERS AND SETTERS
   ========================================================================
    */

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
        //update rules
        notifyRules();
	}

	public TileComponent getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(TileComponent currentTile) {
		this.currentTile = currentTile;
        //update rules
        notifyRules();
	}


    /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

	public void move(Direction direction) {
		//traverse in the space direction
        currentSpace = currentSpace.getAdjacentSpace(direction);
        notifyRules();
	}
	
	public void rotateCurrentTileComponent() {
		currentTile.rotateAround(currentTile);
        notifyRules();
	}


    /*
        This method will return the constructed command
    */
    public GameplayActionCommand getCommand(){
        //check the visitor which type to return
        //set the current space right now and then ask
        visitor.setSpace(currentSpace);
        return visitor.getCommand(currentTile);
    }

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost(){
        return cost;
    }

    /*
        This method will poll the current rules and then construct a response
        from them and return it to stuff
     */
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
