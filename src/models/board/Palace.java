package models.board;

public class Palace extends NotWalkable {
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

    @Override
    public void accept(TileVisitor v) {

    }
}
