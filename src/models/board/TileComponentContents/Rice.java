package models.board.TileComponentContents;

import models.board.TileVisitor;
import models.board.Walkable;

public class Rice extends Walkable {
    @Override
    public void accept(TileVisitor v) {
        v.visit(this);
    }
}
