package board;

public abstract class Space<SpaceType extends Space, TileType extends Tile> {

	/**returns the space adjacent to this space in the given int direction.
	 * As this class is implemented, its subclasses may have their own conventions
	 * for how direction works.*/
	public abstract SpaceType getAdjacent(int direction);
	
	/**places a tile on this space.  As tile is a graph, this
	 * method will probably be implemented recursively.*/
	public abstract void placeTile(TileType tile);
	
	/**returns the height of the uppermost tile on this space*/
	public abstract int getHeight();
	
	/**returns true if and only if the given tile and it's
	 * graph may be placed on this space and its graph without
	 * covering an identical tile graph.
	 * This will likely be done recursively.*/
	public abstract boolean verifyStacking(TileType tile);
	
	/**returns true if and only if all the spaces that would
	 * be covered by the given tile graph are the same height.
	 * This will likely be done recursively.*/
	public abstract boolean veriyHeights(TileType tile);
}
