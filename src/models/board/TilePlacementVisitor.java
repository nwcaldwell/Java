package models.board;

import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Rice;
import models.board.TileComponentContents.Village;

public class TilePlacementVisitor implements TileVisitor {

    private int value;

    public TilePlacementVisitor(){

    }
    
    public void visit(Village v){
        value = 0;
    }

    public void visit(Rice r){
        value = 1;
    }

    @Override
    public void visit(TileComponent tcc) {

    }

    public void visit(Palace p){
        value = 2;
    }

    public void visit(Irrigation i){
        value = 3;
    }
    
    public int getValue() {
    	return value;
    }

}
