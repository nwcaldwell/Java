package models.board;

public class Irrigation extends NotWalkable  {

    public Irrigation(){
        setCanAcceptPalace(false);
    }
	
	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

    @Override
    public void accept(TileVisitor v) {
        throw new UnsupportedOperationException();
    }

    public boolean canAcceptPalace(Palace palace){
        return getCanAcceptPalace();
    }
}
