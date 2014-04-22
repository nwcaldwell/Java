package models.board.TileComponentContents;

import models.board.TilePlacementVisitor;
import models.board.TileVisitor;
import models.board.Walkable;

public class Village extends Walkable {

    public Village(){
        setCanAcceptPalace(true);
    }
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }

    public boolean canAcceptPalace(Palace p){
        return getCanAcceptPalace();
    }
    
    public String toString(){
    	return "village";
    }
}
