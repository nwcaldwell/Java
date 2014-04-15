package models.board;

public abstract class TileComponent <TC extends TileComponent, D extends Direction> {
	private TC[] conjoinedParts;
	
	public abstract TC getConjoinedTile(D direction);
	
	public abstract void rotateAround(TC tileComponent);
	
	private abstract void rotate(TC tileComponent);
}
