package models.board;

import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Rice;
import models.board.TileComponentContents.Village;
import java.util.*;

public class PalaceVisitor implements TileVisitor {

	List<Palace> activePalaces;
	
    public PalaceVisitor(){
    	activePalaces = new ArrayList<Palace>();
    }
    
    public List<Palace> getActivePalaces() {
    	return activePalaces;
    }

    public void visit(TileComponent tcc){

    }

    public void visit(Palace p){
    	if (p.isFaceUp()) {
    		activePalaces.add(p);
    	}
    }

    public void visit(Village v){

    }

    public void visit(Rice r){

    }

    public void visit(Irrigation i){

    }
}
