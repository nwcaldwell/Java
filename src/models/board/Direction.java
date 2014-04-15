package models.board;

public interface Direction {
	public Direction rotate();
	public Direction getDirection(int dir);
	public int getIntValue();
}
