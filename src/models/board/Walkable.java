package models.board;

public abstract class Walkable extends TileComponentContent {
	private boolean walkable;
	
	public Walkable() {
		this.walkable = true;
	}
	
	public boolean getWalkable() {
		return walkable;
	}
}
