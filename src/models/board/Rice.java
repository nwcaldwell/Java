package models.board;

public class Rice extends Walkable {


    @Override
    public void accept(TileVisitor v) {
        v.visit(this);
    }


	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}


}
