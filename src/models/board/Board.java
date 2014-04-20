package models.board;

// Developers [Nathan, WIll, Kevin] Tester [Jorge]


import models.palacefestival.JavaPlayer;

import java.util.ArrayList;

/*

	We added a Board Construction Crew class in order to be able to boards composed of different shapes (squares, triangles, &c.)

 */
public class Board  {
	private Space rootNode;
	private ArrayList<Developer> developers;

	public Board (Direction d, String boardFilePath) {
		//this.rootNode; set this!
		developers = new ArrayList<Developer>();
		rootNode = d.getPreferredBoardConstructionCrew().buildBoard(boardFilePath);
	}

	public Space getRoot() {
		return rootNode;
	}

	public ArrayList<Developer> getDevelopers() {
		return developers;
	}
	
	public Developer getNextDeveloper(Developer currentDeveloper) {
		throw new UnsupportedOperationException();
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
