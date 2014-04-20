package models.board;

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
