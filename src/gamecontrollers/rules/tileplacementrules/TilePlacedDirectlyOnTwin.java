package gamecontrollers.rules.tileplacementrules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.TileCommandCreator;
import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by williammacfarlane on 4/15/14.
 */
public class TilePlacedDirectlyOnTwin extends TilePlacementRule {
    //Hashmap of the spaces occupied by the current tile and the spaces target by the tile
    private HashMap<Space, Integer> spaceReferenceCounter;
    private Message message;
    private TileCommandCreator creator;
    private Space targetSpace;
    private TileComponent targetTile;

    private List<TileComponent> visitedTiles;

    public TilePlacedDirectlyOnTwin(TileCommandCreator creator){
        this.creator = creator;
        spaceReferenceCounter = new HashMap<Space, Integer>();
        visitedTiles = new ArrayList<TileComponent>();
    }

	@Override
	public void update() {
        //clear the old results
		spaceReferenceCounter.clear();

        //grab the target space
        targetSpace = creator.getCurrentSpace();
        //grab the target tile
        targetTile = creator.getCurrentTile();

        //grab the tile on the targetspace
        if(targetSpace.hasTile()) {
            //Space has a tile so everything is all good

            //fill the map out
            fillMap(targetTile, targetSpace);

            //The hash map has been filled os now check if there is a value of one
            //a value of one indicates a unique reference and that the tiles are not oriented the same and the same type
            if(spaceReferenceCounter.containsValue(1)){
                //unique reference found
                message = new Message("Tiles are not the same", false);
                setValidity(true);
            }
            else{
                message = new Message("TileComponent placed directly on a twin", true);
                setValidity(false);
            }

        }
        else{
            //Space has no tile so no good
            message = new Message("Target Space has no tile on top", true);
            setValidity(false);
        }
	}

	@Override
	public Message getErrorMessage() {
		return message;
	}

    /*
        This method will add a mapping to a value of 1 to space keys
        that do not already exist.
        For other space references it will increment the integer key
     */

    private void addToMap(Space space){
        //check if the map already contains the value
        if(spaceReferenceCounter.containsKey(space))
            spaceReferenceCounter.put(space, 1);
        else{
            //space is already in the hash map so add to the integer
            spaceReferenceCounter.put(space, spaceReferenceCounter.get(space) + 1);
        }
    }

      /*
        This method will fill the hashmap
     */

    private void fillMap(TileComponent tile, Space space){
        TileComponent currentTile = space.getTile();


        //Map the existing tilecomponent spaces to the reference counter
        //get the direction iterator from the current tile
        Iterator<Direction> iterator = currentTile.iterator();
        //iterator over the directions to add the spaces
        while (iterator.hasNext()) {
            //get the direction from the current Tile
            Direction direction = iterator.next();
            //check if that current tile has a sibling there
            if (currentTile.siblingExists(direction)) {
                //it has a sibling there so add the space in the same direction if it hasnt been visited already
                if(!visitedTiles.contains(tile.getConjoinedTile(direction)))
                    addToMap(space.getAdjacentSpace(direction));
            }
        }

        //get the direction iterator from the target tile
        Iterator<Direction> iterator2 = tile.iterator();
        //iterator over the directions to add the spaces
        while (iterator2.hasNext()) {
            //check if that current tile has a sibling there
            Direction direction = iterator2.next();
            if (tile.siblingExists(direction)) {
                //it has a sibling there so add the space in the same direction
                //NOT CHECKING IF THE SPACE EXISTS
                //it has a sibling there so add the space in the same direction if it hasnt been visited already
                if(!visitedTiles.contains(tile.getConjoinedTile(direction))){
                    addToMap(space.getAdjacentSpace(direction));
                    fillMap(tile.getConjoinedTile(direction), space.getAdjacentSpace(direction));
                }

            }
        }

        //add this tile to the visited tiles list if it hasnt already been added
        if(!visitedTiles.contains(tile)){
            visitedTiles.add(tile);
        }


    }


}
