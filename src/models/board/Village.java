package models.board;

public class Village extends Walkable implements VisitableTile {
	
	public void accept(TilePlacementVisitor v) {
		throw new UnsupportedOperationException();
	}
}
