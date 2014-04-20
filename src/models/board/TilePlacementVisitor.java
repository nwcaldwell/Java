package models.board;

public class TilePlacementVisitor implements TileVisitor {

	private int value;
	
    public TilePlacementVisitor() {

    }

    public void visit(Rice r) {
        value = 0;
    }
    
    public void visit(Village v) {
        value = 1;
    }
  
    public void visit(Palace p) {
        value = 2;
    }

    public void visit(Irrigation i) {
        value = 3;
    }

    public void visit(TileComponent tcc) {
        value = 4;
    }
    
    public int getValue(){
        return value;
    }
}
