package models.board;

public class Rice extends Walkable {

	public void accept(TileVisitor v) {
		v.visit(this);
	}
}
