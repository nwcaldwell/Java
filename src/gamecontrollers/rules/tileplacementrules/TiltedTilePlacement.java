package gamecontrollers.rules.tileplacementrules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.TileCommandCreator;
import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by williammacfarlane on 4/14/14.
 */
public class TiltedTilePlacement extends TilePlacementRule {
    private Message message;
    private TileCommandCreator creator;
    private Space targetSpace;
    private TileComponent targetTile;
    private List<Space> spacesToBeTouched;
    private List<TileComponent> visitedTiles;

    public TiltedTilePlacement(TileCommandCreator creator){
        this.creator = creator;
        spacesToBeTouched = new ArrayList<Space>();
        visitedTiles = new ArrayList<TileComponent>();
    }

	@Override
	public void update() {
        //clear last update
        spacesToBeTouched.clear();
        visitedTiles.clear();

        targetSpace = creator.getCurrentSpace();
        targetTile = creator.getCurrentTile();

        populateList(targetTile, targetSpace);

        //this is the height of the space underneath the target tile
        //use this height to check the height of all other spaces
        int referenceHeight = targetSpace.getHeight();

        //boolean to know if tilting was detected
        boolean isTilted = false;
        if(visitedTiles.size() > 0)
            for(Space space : spacesToBeTouched){
                if(referenceHeight != space.getHeight()){
                    isTilted = true;
                    break;
                }
            }

        if(isTilted){
            message = new Message("TILTED TILE", true);
            setValidity(false);
        }
        else{
            message = new Message("tile is level", false);
            setValidity(true);
        }


	}

	@Override
	public Message getErrorMessage() {
		return message;
	}

    //populate the list of affected spaces
    private void populateList(TileComponent tile, Space space){
        //get the directions iterator from the target tile
        if(tile != null)
            visitedTiles.add(tile);

        if(tile != null && space != null) {
            spacesToBeTouched.add(space);
            Iterator<Direction> iterator = tile.getDirection().iterator();

            //check the spaces under the target tile for the same height
            while (iterator.hasNext()) {
                Direction direction = iterator.next();
                if (tile.siblingExists(direction)) {
                    //tile is joined in this direction so the space in this direction will be touched
                    //add it if it hasnt already been added
                    if (!spacesToBeTouched.contains(space))
                        spacesToBeTouched.add(space.getAdjacentSpace(direction));


                    //if that tile hasnt been visited then visit it
                    if (!visitedTiles.contains(tile.getConjoinedTile(direction)))
                        populateList(tile.getConjoinedTile(direction), space.getAdjacentSpace(direction));
                }

            }
        }



    }
}
