package models.board;

import java.util.Iterator;

//TODO [Nathan, Will, Kevin] [Jorge]
public interface Direction {
	public Direction rotate();
	public int getIntValue();
	public int numDirections();
	public Iterator<Direction> iterator();
	public BoardConstructionCrew getPreferredBoardConstructionCrew();
}