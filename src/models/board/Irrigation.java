package models.board;

public class Irrigation extends NotWalkable  {
	
	public void accept(TilePlacementVisitor v) {
		throw new UnsupportedOperationException();
	}

    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }
}
