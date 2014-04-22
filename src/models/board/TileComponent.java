package models.board;

import gamecontrollers.commandcreator.TileCreationVisitor;

import java.util.HashSet;
import java.util.Iterator;

public class TileComponent {
    private TileComponentContent tcc;
    private Direction direction;
    private TileComponent[] conjoinedParts;

    public TileComponent(Direction d, TileComponentContent tcc) {
        this.direction = d;
        conjoinedParts = new TileComponent[d.numDirections()];
        this.tcc = tcc;

    }

    public TileComponent getConjoinedTile(Direction direction) { //should call siblingExists(dir) first!!!
        return conjoinedParts[direction.getIntValue()];
    }

	public boolean siblingExists(Direction direction)
	{
		return (conjoinedParts[direction.getIntValue()] != null) ? true : false;
	}
	public void setSiblings(TileComponent[] siblings)
	{
		conjoinedParts = siblings;
	}
	public void rotateAround(TileComponent tileComponent){ //The tileComponent passed in does NOT have to be a center
		rotate(tileComponent, new HashSet<TileComponent>());
    }

    public TileComponentContent getTileComponentContent() {
        return tcc;
    }


    /**
     * *********
     * <p/>
     * Private rotation stuff!
     */
    private void rotate(TileComponent tileComponent, HashSet<TileComponent> visited) {
        //visitation privileges
        if (visited.contains(tileComponent))
            return; //been there before
        visited.add(tileComponent);

        //rotate your edges
        rotateEdges(tileComponent);
        //do the same for your neighbors
        rotateNeighbors(tileComponent, visited);
    }

    private void rotateEdges(TileComponent tileComponent) {
        //rotate all edges
        TileComponent last = conjoinedParts[direction.numDirections() - 1];
        Iterator<Direction> ita = direction.iterator();
        Iterator<Direction> itb = direction.rotate().iterator(); //B should be one side ahead of A
        TileComponent[] newCohorts=new TileComponent[conjoinedParts.length];

        while (itb.hasNext()){
            newCohorts[itb.next().getIntValue()] = conjoinedParts[ita.next().getIntValue()];
        }

        conjoinedParts=newCohorts;
        conjoinedParts[0] = last; //making the rotations go full circle
    }

    private void rotateNeighbors(TileComponent tileComponent, HashSet<TileComponent> visited) {
        Iterator<Direction> it = direction.iterator();
        Direction dir;
        while (it.hasNext()) {
            dir = it.next();
            if (tileComponent.siblingExists(dir))
            	tileComponent.getConjoinedTile(dir).rotate(tileComponent.getConjoinedTile(dir), visited);
        }
    }


    public Direction getDirection() {
        return direction;
    }

    public void accept(TileCreationVisitor visitor) {
        visitor.visit(this);
    }

    public Iterator<Direction> iterator(){
        return direction.iterator();
    }
}
