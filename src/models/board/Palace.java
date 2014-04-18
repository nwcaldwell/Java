package models.board;

public class Palace extends NotWalkable implements VisitableTile {
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}
}
