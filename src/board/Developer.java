package board;

public class Developer {
	private Player player;
	private Space space;
	
	public Developer(Player player, Space space) {
		this.player = player;
		this.space = space;
	}

	public Player getPlayer() {
		return player;
	}

	public Space getSpace() {
		return space;
	}	
	
	public void updateLocation(Space s) {
		
	}
}
