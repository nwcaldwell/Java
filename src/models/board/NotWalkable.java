package models.board;

public abstract class NotWalkable extends TileComponentContent {
	private boolean walkable;
	
	public NotWalkable() {
		this.walkable = false;
	}
	
	public boolean getWalkable() {
		return walkable;
	}
}

