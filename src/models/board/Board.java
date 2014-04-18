package models.board;

//TODO [Nathan, WIll, Kevin][Jorge]


import models.palacefestival.JavaPlayer;

public class Board  {
	private Space rootNode;
	private Developer[] developers;
	
	public Board (Space rootNode, Developer[] developers) {
		this.rootNode = rootNode;
		this.developers = new Developer[12];
	}

	public Space getRoot() {
		return rootNode;
	}

	public Developer[] getDevelopers() {
		return developers;
	}
	
	public Developer getNextDeveloper(Developer currentDeveloper) {
		return null;
	}
	
	public Developer getFirstDeveloper(JavaPlayer p) {
		for (Developer dev : developers) {
			if (dev.notNull()) {
				return dev;
			}
		}
		
		return null;
	}

}
