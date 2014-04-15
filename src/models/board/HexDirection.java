package models.board;

import java.util.Iterator;

//TODO [Nathan, Will, Kevin] [Jorge]

public enum HexDirection implements Direction, Iterable<HexDirection>{
	N,NE,SE,S,SW,NW;

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
	public Iterator<HexDirection> iterator()
	{
		return new HexDirectionIterator();
	}

	private class HexDirectionIterator implements Iterator<HexDirection>
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
			throw new UnsupportedOperationException("You can't remove.");
		}
	}

}
