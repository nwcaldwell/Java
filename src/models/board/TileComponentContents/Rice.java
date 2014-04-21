package models.board.TileComponentContents;

import models.board.TileVisitor;
import models.board.Walkable;

public class Rice extends Walkable {
    public Rice(){
        setCanAcceptPalace(false);
    }
    @Override
    public void accept(TileVisitor v) {
        v.visit(this);
    }

    public boolean canAcceptPalace(Palace p){
        return getCanAcceptPalace();
    }
}
