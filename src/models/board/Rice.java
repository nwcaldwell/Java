package models.board;

public class Rice extends Walkable {


    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }

	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

}
