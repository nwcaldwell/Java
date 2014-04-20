package models.board;

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
}
