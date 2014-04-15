package models.board;

public enum HexDirection implements Direction {
	N,NE,SE,S,SW,NW;

	@Override
	public HexDirection rotate() {
		return getDirection((this.ordinal() + 1) % this.values().length);
	}
	public HexDirection getDirection(int dir)
	{
		return values()[dir];
	}
	public int getIntValue()
	{
		return this.ordinal();

	}
}
