package models.board;

import java.util.Iterator;

//TODO [Nathan, Will, Kevin] [Jorge]

public enum HexDirection implements Direction, Iterable<Direction>{
	N,NE,SE,S,SW,NW;
	static BoardConstructionCrew preferredBoardConstructionCrew = new HexBoardConstructionCrew();

	@Override
	public BoardConstructionCrew getPreferredBoardConstructionCrew() {
		return preferredBoardConstructionCrew;
	}


	@Override
	public HexDirection rotate() {
		return values()[(this.ordinal() + 1) % numDirections()];
	}
	public int getIntValue()
	{
		return this.ordinal();
	}
	public int numDirections()
	{
		return this.values().length;
	}
	public Iterator<Direction> iterator()
	{
		return new HexDirectionIterator();
	}

	private class HexDirectionIterator implements Iterator<Direction>
	{

		int offset;
		int counter;
		
		public HexDirectionIterator() {
			offset = HexDirection.this.getIntValue();
			counter = 0;
		}
		
		@Override
		public boolean hasNext() {
			return counter<HexDirection.values().length;
		}

		@Override
		public HexDirection next() {
			HexDirection direction = HexDirection.values()[(offset+counter)%HexDirection.values().length];
			counter++;
			return direction;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Iterator removal of a direction not supported.");
		}
	}

}
