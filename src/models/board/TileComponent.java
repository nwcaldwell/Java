package models.board;

public class TileComponent {
    private Direction direction;
    private TileComponent[] conjoinedParts;

    TileComponent(Direction d){
        this.direction = d;
        conjoinedParts = new TileComponent[d.getIntValue()];
    }
	
	public TileComponent getConjoinedTile(Direction direction){
        return conjoinedParts[direction.getIntValue()];
    }
	
	public void rotateAround(TileComponent tileComponent){

    }
}

