// New package from design doc. Used to collect all active palaces.

package models.board;

import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Village;
import models.board.TileComponentContents.Rice;

import java.util.ArrayList;
import java.util.List;

public class PalaceVisitor implements TileVisitor {

	List<Palace> activePalaces;
	
    public PalaceVisitor() {
    	ArrayList<Palace> activePalaces = new ArrayList<Palace>();
    }

    public List<Palace> getActivePalaces() {
    	return activePalaces;
    }
    
    public void visit(TileComponent tcc) {

    }

    public void visit(Village v) {
    
    }
    
    public void visit(Rice p) {
    
    }
    
    public void visit(Palace p) {
    	if (p.isFaceUp()) {
    		activePalaces.add(p);
    	}
    }
    
    public void visit(Irrigation i) {
    	
    }
}
