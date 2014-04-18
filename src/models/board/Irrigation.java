package models.board;

public class Irrigation extends NotWalkable  {
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }
}
