package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;

public class TilePlacementController <A extends Space, B extends TileComponent> extends TileCommandCreator {
	private Space currentSpace;
	private TileComponent currentTile;
	
	public TilePlacementController(Space currentSpace, TileComponent currentTile) {
		this.currentSpace = currentSpace;
		this.currentTile = currentTile;
	}
	
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
	
	public void placeTileComponent() {
		
	}
	
	public void move(Direction direction) {
		
	}
	
	public void rotateCurrentTileComponent() {
		
	}


    /*
    This method will return the constructed command
 */
    public GameplayActionCommand getCommand(){

        // TODO implement
        return null;

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
