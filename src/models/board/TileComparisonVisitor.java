package models.board;

import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Rice;
import models.board.TileComponentContents.Village;

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
        t1.accept(this);
        int firstTileVal = value;
        t2.accept(this);

        return (firstTileVal == value);
    }

    public int getValue(){
        return value;
    }
}
