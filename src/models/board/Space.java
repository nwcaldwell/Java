package models.board;

import java.util.Stack;

public class Space<D extends Direction> {

	private Space[] neighbors = new Space[D.numDirections()];
	private boolean[] neighborExists;
    private Stack<TileComponent> tileStack = new Stack<TileComponent>();
    protected int numNeighbors;


	/**returns the space adjacent to this space in the given int direction.
	 * As this class is implemented, its subclasses may have their own conventions
	 * for how direction works.*/
	public Space getAdjacentSpace(D direction){
        return neighbors[direction.getIntValue()];
    }

	
	/**places a tile on this space.  As tile is a graph, this
	 * method will probably be implemented recursively.*/
	public void placeTile(TileComponent tile){
        tileStack.push(tile);
    }

    public TileComponent getTile(){
        return tileStack.peek();
    }
	
	/**returns the height of the uppermost tile on this space*/
	public int getHeight(){
        return tileStack.size();
    }

    protected void setNeighbors(Space[] array){
        this.neighbors = array;
    }

    protected Space getNeighbor(D direction){
        return neighbors[direction.getIntValue()];
    }

    protected void setNeighbor(D direction, Space space){
        neighbors[direction.getIntValue()] = space;
    }


}
