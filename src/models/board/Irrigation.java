package models.board;

public class Irrigation extends NotWalkable implements VisitableTile {
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}
}
