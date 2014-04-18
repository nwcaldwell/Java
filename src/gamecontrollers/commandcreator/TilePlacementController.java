package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.PlaceTileCommand;
import gamecontrollers.rules.Rule;
import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;

import java.util.ArrayList;

public class TilePlacementController extends TileCommandCreator {
	private Space currentSpace;
	private TileComponent currentTile;
    private ArrayList<Rule> rules;

    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */
	public TilePlacementController(Space currentSpace, TileComponent currentTile) {
		this.currentSpace = currentSpace;
		this.currentTile = currentTile;
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
	}

	public TileComponent getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(TileComponent currentTile) {
		this.currentTile = currentTile;
	}


    /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

	public void move(Direction direction) {
		//traverse in the space direction
        currentSpace = currentSpace.getAdjacentSpace(direction);
	}
	
	public void rotateCurrentTileComponent() {
		currentTile.rotateAround(currentTile);
	}


    /*
        This method will return the constructed command
    */
    public GameplayActionCommand getCommand(){

        GameplayActionCommand command = new PlaceTileCommand(currentTile, currentSpace);

        return command;
    }

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost(){
        return 0;
    }

    /*
        This method will poll the current rules and then construct a response
        from them and return it to stuff
     */
    public Response checkPossible(){
        Response response = new Response();

        for(Rule rool : rules) {
            //response.addMessage(rool.getErrorMessage());
        }
        return response;
    }
	
}
