package models.board;

public class Village extends Walkable {
	
	public void accept(TilePlacementVisitor v) {
		throw new UnsupportedOperationException();
	}

    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }
}
