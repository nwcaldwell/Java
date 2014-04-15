package models.board;

import java.util.Stack;

//TODO [Kevin][Jorge, Kevin]

public class Space {

	private Space[] neighbors;
	private boolean[] neighborExists;
    private Stack<TileComponent> tileStack = new Stack<TileComponent>();
    protected int numNeighbors;
    private Direction direction;

    Space(Direction d)
    {
        direction = d;
        neighbors = new Space[d.numDirections()];
    }

	/**returns the space adjacent to this space in the given int direction.
	 * As this class is implemented, its subclasses may have their own conventions
	 * for how direction works.*/
	public Space getAdjacentSpace(Direction direction){
        return neighbors[direction.getIntValue()];
    }

	
	/**places a tile on this space.  As tile is a graph, this
	 * method will probably be implemented recursively.*/
	public void placeTile(TileComponent tile){
        tileStack.push(tile);
    }

    public TileComponent getTile(){
        return tileStack.peek();
    }
	
	/**returns the height of the uppermost tile on this space*/
	public int getHeight(){
        return tileStack.size();
    }

    protected void setNeighbors(Space[] array){
        this.neighbors = array;
    }

    protected Space getNeighbor(Direction direction){
        return neighbors[direction.getIntValue()];
    }

    protected void setNeighbor(Direction direction, Space space){
        neighbors[direction.getIntValue()] = space;
    }
    
    public void removeTile() {
    	tileStack.pop();
    }

}
