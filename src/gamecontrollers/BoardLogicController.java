package gamecontrollers;

import gamecontrollers.checks.*;
import models.board.*;
import models.palacefestival.JavaPlayer;

import java.util.*;

public class BoardLogicController {
	private Board board;
	private int numTiles;
    private DevMoveController devMoveController;

	public BoardLogicController(Board board) {
		numTiles = getAllSpaces().size();
		this.board = board;
		this.devMoveController = new DevMoveController(this);
	}

	public Board getBoard() {
		return board;
	}
	
	public List<Space> getCitySpaces(Space s) {
		return new ArrayList<Space>();
	}
	
	public List<Space> getPoolAndBeach(Space s, List<Space> pool, List<Space> beach) {
		return new ArrayList<Space>();
	}

	public int moveDeveloperWithinCentralJava(JavaPlayer p, Space origin, Space destination, List<Space> path)
	{
		return devMoveController.nodeScoreToActionPointConversion(devMoveController.findPathWithinCentralJava(p, origin, destination, path));
	}
	public int moveDeveloperOnBoard(JavaPlayer p, Space destination, List<Space> path) { // n
		return devMoveController.nodeScoreToActionPointConversion(devMoveController.findOnBoardPath(p, destination, path));
	}
	public int moveDeveloperOffBoard(JavaPlayer p, Space origin, List<Space> path) // n
	{
		return devMoveController.nodeScoreToActionPointConversion(devMoveController.findOffBoardPath(p, origin, path));
	}

	//Assuming the board is a connected graph...
	public List<Space> getAllSpaces()
	{
		Space root = board.getRoot();
		List<Space> allSpaces = new LinkedList<Space>();
		LinkedList<Space> q = new LinkedList<Space>();
		HashSet<Space> visited = new HashSet<Space>();
		q.offer(root);
		visited.add(root);

		while(!q.isEmpty())
		{
			Space space = q.poll();
			allSpaces.add(space);
			Iterator<Direction> it = space.getDirections().iterator();
			while(it.hasNext())
			{
				Direction dir = it.next();
				if(!space.hasAdjacentSpace(dir))
					continue;
				Space neighbor = space.getAdjacentSpace(dir);
				if(visited.contains(neighbor))
					continue;
				visited.add(neighbor);
				q.offer(neighbor);
			}
		}
		return allSpaces;
	}
	public int getNumTiles()
	{
		return numTiles;
	}
	public boolean inCentralJava(Space space)
	{
		return space.isInCentralJava();
	}

	
	public boolean validPlacement(TileComponent t, Space s) {
		return false;
	}
	
	public List<Space> getAllPlacedPalaces() {
		return null;
	}
	
	public Developer getNextDeveloper(Developer d) {
        return board.getNextDeveloper(d);
	}
	
	public boolean hasDeveloper(Space s, Developer d) {
		return false;
	}

    public boolean hasDeveloperOn(Space s){
        return board.hasDeveloperOn(s);
    }

    public Developer getDeveloperOn(Space space){
        Developer dev = new Developer();
        ArrayList<Developer> devs = board.getDevelopers();

        for(Developer temp : devs){
            if(temp.getSpace() == space){
                dev = temp;
            }
        }
        return dev;
    }

}
