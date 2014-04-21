package models.board.TileComponentContents;

import models.board.NotWalkable;
import models.board.TilePlacementVisitor;
import models.board.TileVisitor;

public class Irrigation extends NotWalkable {
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }
}
