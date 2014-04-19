package models.board;

import models.palacefestival.JavaPlayer;

public class Developer {
	private JavaPlayer player;
	private Space space;
	
	public Developer(JavaPlayer player, Space space) {
		this.player = player;
		this.space = space;
	}

	public JavaPlayer getPlayer() {
		return player;
	}

	public Space getSpace() {
		return space;
	}	
	
	public void updateLocation(Space s) {
		
	}

	public boolean notNull() {
		return this == null;
	}
}
