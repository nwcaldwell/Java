package board;

public class TilePlacementController <A extends Space, B extends Tile> {
	private Space currentSpace;
	private TileComponent currentTile;
	
	public TilePlacementController(Space currentSpace, TileComponent currentTile) {
		this.currentSpace = currentSpace;
		this.currentTile = currentTile;
	}
	
	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}

	public TileComponent getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(TileComponent currentTile) {
		this.currentTile = currentTile;
	}
	
	public void placeTileComponent() {
		
	}
	
	public void move(Direction direction) {
		
	}
	
	public void rotateCurrentTileComponent() {
		
	}
	
	
}
