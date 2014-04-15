package board;

import java.util.List;
import java.util.ArrayList;

public class BoardLogicController {
	private Board board;
	
	public BoardLogicController(Board board) {
		
	}

	public Board getBoard() {
		return board;
	}
	
	public boolean isBoarderingLowlands() {
		return false;
	}

	public boolean isBoarderingMountains() {
		return false;
	}
	
	public List<Space> getCitySpaces(Space s) {
		return new ArrayList<Space>();
	}
	
	public List<Space> getPoolAndBeach(Space s, List<Space> pool, List<Space> beach) {
		return new ArrayList<Space>();
	}
	
	public boolean isOutOfBounds(Space s) {
		return false;
	}
	
	public int findShortestPath(Player p, Space origin, Space destination, List<Space> path) {
		return 0;
	}
	
	public void moveDeveloperOnBoard(Player p, Space s) {
		
	}
	
	public boolean validPlacement(TileComponent t, Space s) {
		return false;
	}
	
	public List<Space> getAllPlacedPalaces() {
		
	}
	
	public Developer getNextDeveloper(Developer d) {
		
	}
	
	public boolean hasDeveloper(Space s, Developer d) {
		
	}
}
