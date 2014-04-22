package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.checks.WouldTileComponentBeOnBoard;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.PlaceTileCommand;
import gamecontrollers.rules.Rule;
import gamecontrollers.rules.tileplacementrules.TilePlacedDirectlyOnTwin;
import gamecontrollers.rules.tileplacementrules.TilePlacementRule;
import gamecontrollers.rules.tileplacementrules.TiltedTilePlacement;
import gamecontrollers.turn.TurnController;
import models.board.Direction;
import models.board.SharedResources;
import models.board.Space;
import models.board.TileComponent;

import java.util.ArrayList;
import java.util.List;

import view.ViewController;

public class TilePlacementCommandCreator extends TileCommandCreator {
	private Space currentSpace;
	private TileComponent currentTile;
    private ArrayList<TilePlacementRule> rules = new ArrayList<TilePlacementRule>();
    private TurnController controller;
    private int cost;
    private TileCreationVisitor visitor;
    private SharedResources resources;
    private WouldTileComponentBeOnBoard onBoardChecker;

    private List<Direction> pathToTile;
    
    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */

	public TilePlacementCommandCreator(TurnController controller, SharedResources resources) {
        this.controller = controller;
        visitor = new TileCreationVisitor(controller, resources);
        this.resources = resources;
        onBoardChecker = new WouldTileComponentBeOnBoard();

        TiltedTilePlacement tiltedRule = new TiltedTilePlacement(this);
        TilePlacedDirectlyOnTwin twinRule = new TilePlacedDirectlyOnTwin(this);
        rules.add(tiltedRule);
        rules.add(twinRule);
        pathToTile=new ArrayList<Direction>();
    }


    /*
   ========================================================================
      GETTERS AND SETTERS
   ========================================================================
    */

    public void setTurnController(TurnController controller){
        this.controller = controller;
        visitor = new TileCreationVisitor(controller, resources);
    }

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
        pathToTile.clear();
        notifyRules();
	}

	public TileComponent getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTileComponent(TileComponent currentTileComponent) {
		this.currentTile = currentTileComponent;
        //update rules
        notifyRules();
	}


    /*
  ========================================================================
     PUBLIC METHODS
  ========================================================================
   */

	public void move(Direction direction) {
        //check for if we are moving off board
        if(onBoardChecker.check(currentSpace.getAdjacentSpace(direction), currentTile)){
            //its all good so do the moving
            //traverse in the space direction
        	pathToTile.add(direction);
            currentSpace = currentSpace.getAdjacentSpace(direction);
            notifyRules();
        }

	}
	
	public void rotateCurrentTileComponent() {
        //rotate this guy momentarily and run the onboard check
        currentTile.rotateAround(currentTile);
        if(onBoardChecker.check(currentSpace, currentTile)) {
            //its okay to do
            //keep the change and notify the rules
            notifyRules();
        }
        else{
            //rotate was bad and you cant do that
            //need to rotate back to original position
            for(int i = 0; i < currentTile.getDirection().numDirections() - 1; i ++){
                currentTile.rotateAround(currentTile);
            }
        }
	}


    /*
        This method will return the constructed command
    */
    public GameplayActionCommand getCommand(){
        //check the visitor which type to return
        //set the current space right now and then ask
        visitor.setSpace(currentSpace);
        GameplayActionCommand command = visitor.getCommand(currentTile);
        return command;

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
        notifyRules();

        for(Rule rool : rules) {
            response.addMessage(rool.getErrorMessage());
        }
        return response;
    }

    public List<Direction> getPath() {
    	return pathToTile;
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
