package models.board;

public class Rice extends Walkable implements VisitableTile {

	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}
}
