package models.board;

public abstract class Space<A extends Space, B extends Tile, D extends Direction> {

	private B[] neighbors;
	private boolean[] neighborExists;
	
	/**returns the space adjacent to this space in the given int direction.
	 * As this class is implemented, its subclasses may have their own conventions
	 * for how direction works.*/
	public abstract A getAdjacentSpace(D direction);
	
	/**places a tile on this space.  As tile is a graph, this
	 * method will probably be implemented recursively.*/
	public abstract void placeTile(B tile);
	
	/**returns the height of the uppermost tile on this space*/
	public abstract int getHeight();
	
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
}
