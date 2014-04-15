package board;

public class DevMoveController <A extends Space, B extends Tile> {
	private Space desiredSpace;
	private Developer currentDeveloper;
	
	public DevMovementController(Space desiredSpace, Developer currentDeveloper) {
		this.desiredSpace = desiredSpace;
		this.currentDeveloper = currentDeveloper;
	}
	
	public Space getDesiredSpace() {
		return desiredSpace;
	}

	public void setDesiredSpace(Space desiredSpace) {
		this.desiredSpace = desiredSpace;
	}

	public Developer getCurrentDeveloper() {
		return currentDeveloper;
	}

	public void setCurrentDeveloper(Developer currentDeveloper) {
		this.currentDeveloper = currentDeveloper;
	}
	
	public void iterateThroughBoardDevelopers() {
		
	}
	
	public void move(Direction direction) {
		
	}
	
}
