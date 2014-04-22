package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.checks.WouldTileComponentBeOnBoard;
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
import java.util.Iterator;
import java.util.List;

public class TilePlacementCommandCreator extends TileCommandCreator {
	private Space currentSpace;
	private TileComponent currentTile;
    private ArrayList<TilePlacementRule> rules = new ArrayList<TilePlacementRule>();
    private TurnController controller;
    private int cost;
    private TileCreationVisitor visitor;
    private SharedResources resources;
    private WouldTileComponentBeOnBoard onBoardChecker;
    private List<Space> targetSpaces;
    private List<Space> visited;

    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */

	public TilePlacementCommandCreator(TurnController controller, SharedResources resources) {
        this.controller = controller;
        this.visitor = new TileCreationVisitor(controller, resources);
        this.resources = resources;
        this.onBoardChecker = new WouldTileComponentBeOnBoard();
        this.targetSpaces = new ArrayList<Space>();
        this.visited = new ArrayList<Space>();
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
        //update rules
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
        return visitor.getCommand(currentTile);

    }

    /*
        This method will return the AP cost of the command that would
        be required to perform the command
     */
    public int getCost(){
        calculateCost();
        return cost;
    }

    public void calculateCost(){
        visited.clear();
        targetSpaces.clear();
        cost = 1;
        fillMeSomeMaps(currentSpace, currentTile);

        for(Space space : targetSpaces){
            if(space.isInHighlands()) cost += 2;
            else if(space.isInLowlands()) cost++;
        }
    }

    private void fillMeSomeMaps(Space space, TileComponent tile){

        if(!targetSpaces.contains(currentSpace)){
            targetSpaces.add(currentSpace);
        }
        //add this space to visited
        visited.add(space);

        Iterator<Direction> iterator = tile.iterator();
        while (iterator.hasNext()) {
            //get the direction from the current Tile
            Direction direction = iterator.next();
            //check if that current tile has a sibling there
            if (tile.siblingExists(direction) && space.hasAdjacentSpace(direction)) {
                if(!visited.contains(space.getAdjacentSpace(direction))) {
                    //theres a guy here so add him and do more visit
                    targetSpaces.add(space.getAdjacentSpace(direction));
                    fillMeSomeMaps(space.getAdjacentSpace(direction), tile.getConjoinedTile(direction));
                }
            }
        }
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
