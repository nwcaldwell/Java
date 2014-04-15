package models.board;

public abstract class Tile<TileType extends Tile> {
	
	/**returns the tile adjacent to this tile in the given int direction.
	 * As this class is implemented, its subclasses may have their own conventions
	 * for how direction works.*/
	public abstract TileType getAdjacent(int direction);
	
	/**rotates this tile.  As this is a grid,
	 * this will probably be done recursively.*/
	public abstract void rotate();
}
