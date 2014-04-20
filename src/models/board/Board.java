package models.board;

//TODO [Nathan, WIll, Kevin][Jorge]


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
		Developer temp = currentDeveloper;

        //yo this works dont listen jorge
        //find a developer and then set that to the return value
        //remove the developer and put it at the end of the list
        //on the next search then you will find a different problem
        loop: for(Developer dev : developers){
            if(dev.getPlayer() == currentDeveloper.getPlayer()){
                temp = dev;
                developers.remove(dev);
                developers.add(dev);
                break loop;
            }
        }

        return temp;
	}
	
	public Developer getFirstDeveloper(JavaPlayer p) {
		for (Developer dev : developers) {
			if (dev.notNull()) {
				return dev;
			}
		}
		
		return null;
	}

    public boolean hasDeveloperOn(Space s){
        for(Developer dev : developers){
            if(dev.getSpace() == s)
                return true;
        }

        return false;
    }

}
