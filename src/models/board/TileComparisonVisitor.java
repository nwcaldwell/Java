package models.board;

// TODO comeback to this class
public class TileComparisonVisitor implements TileVisitor {

    private int value;

    public void visit(TileComponent tcc){
        value = 0;
    }

    public void visit(Palace p){
        value = 1;
    }

    public void visit(Village v){
        value = 2;
    }

    public void visit(Rice r){
        value = 3;
    }

    public void visit(Irrigation i){
        value = 4;
    }

    public boolean compare(TileComponentContent t1, TileComponentContent t2) {
        return false;
    }

    public int getValue(){
        return value;
    }
}
