package models.board;

public class Board <A extends Space, B extends Tile> {
	private Space<A,B> rootNode;
	private Developer[] developers;
	
	public Board (Space<A,B> rootNode, Developer[] developers) {
		this.rootNode = rootNode;
		this.developers = new Developer[12];
	}

	public Space<A, B> getRoot() {
		return rootNode;
	}

	public Developer[] getDevelopers() {
		return developers;
	}
	
	public Developer getNextDeveloper(Developer currentDeveloper) {
		return new Developer();
	}
	
	public Developer getFirstDeveloper(Player p) {
		return new Developer();
	}
}
