package gamecontrollers.checks;

import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kevinnieman on 4/21/14.
 */
public class WouldTileComponentBeOnBoard {
    private List<TileComponent> visitedTiles;

    public WouldTileComponentBeOnBoard(){
        visitedTiles = new ArrayList<TileComponent>();
    }

    public boolean check(Space space, TileComponent tile){
        //clear any old calculations
        visitedTiles.clear();

        //innocent until proven guilty
        boolean onBoard = true;

        //this tile has been visited so add it to the list of visited tiles
        visitedTiles.add(tile);

        //you just moved off the board son, thats a big mistake buddy
        if(space == null)
            return false;

        //need to check if the attached tileComponent has neighbors where the space doesnt
        //get a direciton iterator from the given tile
        Iterator<Direction> iterator = tile.getDirection().iterator();

        //with this direction iterator iterate over the directions of the given tile
        while(iterator.hasNext()){
            //get the current direction
            Direction direction = iterator.next();
            //check if the tile has a conjoined tile in this direction
            if(tile.siblingExists(direction)){
                //tile has a conjoined tile in this direction
                //check if the space has a neighbor in this direction
                if(!space.hasAdjacentSpace(direction)){
                    //if the space doesnt have an adjacent space in this direction you are hanging over the board
                    return false;
                }
                else{
                    //here we know two things
                    //the space has a conjoined space in this direction
                    //the tile has a conjoined tile in this direction
                    //therefore continue to make the checks given that this tile has not been visited
                    if(!visitedTiles.contains(tile))
                        onBoard = onBoard && check(space.getAdjacentSpace(direction), tile.getConjoinedTile(direction));
                }
            }
        }

        return onBoard;
    }
}
