package models.board;

public interface VisitableTile {
	public void accept(TilePlacementVisitor v);
}
