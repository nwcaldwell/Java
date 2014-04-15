package models.board;

import java.util.Stack;

public abstract class Space<A extends Space, B extends TileComponent, D extends Direction> {

	private A[] neighbors;
	private boolean[] neighborExists;
    private Stack<B> tileStack = new Stack<B>();
	
	/**returns the space adjacent to this space in the given int direction.
	 * As this class is implemented, its subclasses may have their own conventions
	 * for how direction works.*/
	public abstract A getAdjacentSpace(D direction);
	
	/**places a tile on this space.  As tile is a graph, this
	 * method will probably be implemented recursively.*/
	public void placeTile(B tile){
        tileStack.push(tile);
    }

    public B getTile(){
        return tileStack.peek();
    }
	
	/**returns the height of the uppermost tile on this space*/
	public int getHeight(){
        return tileStack.size();
    }
	
	/**returns true if and only if the given tile and it's
	 * graph may be placed on this space and its graph without
	 * covering an identical tile graph.
	 * This will likely be done recursively.*/
	public abstract boolean verifyStacking(B tile);
	
	/**returns true if and only if all the spaces that would
	 * be covered by the given tile graph are the same height.
	 * This will likely be done recursively.*/

	public abstract boolean verifyHeights(B tile);
	
	public abstract boolean neighborExists(D direction);

    protected void setNeighbors(B[] array){
        this.neighbors = array;
    }

    protected B getNeighbor(D direction){
        return neighbors[direction.ordinal()];
    }

    protected void setNeigbor(D direction, A space){
        neighbors[direction.ordinal()] = space;
    }


}
