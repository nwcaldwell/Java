package models.board;

public class HexTileComponent extends TileComponent<HexTileComponent, HexDirection> {


	@Override
	public HexTileComponent getConjoinedTile(HexDirection direction) {
		return null;
	}

	@Override
	public void rotateAround(HexTileComponent tileComponent) {

	}

	@Override
	protected void rotate(HexTileComponent tileComponent) {

	}
}
