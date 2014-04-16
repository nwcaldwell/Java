package models.board;

public class Palace extends NotWalkable {
	
	public void accept(TilePlacementVisitor v) {
		throw new UnsupportedOperationException();
	}

    @Override
    public void accept(TileVisitor v) {

    }
}
