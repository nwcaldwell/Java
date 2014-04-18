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

		@Override
		public boolean hasNext() {
			return getIntValue() != numDirections() - 1;
		}

		@Override
		public HexDirection next() {
			return rotate();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Iterator removal of a direction not supported.");
		}
	}

}
