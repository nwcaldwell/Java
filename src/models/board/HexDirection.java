package models.board;

public enum HexDirection implements Direction {
	N,NE,SE,S,SW,NW;

	@Override
	public HexDirection rotate() {
		return values()[(this.ordinal() + 1) % this.values().length];
	}
}
