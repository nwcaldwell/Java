package models;

import java.awt.Color;
import java.util.ArrayList;

public class JavaPlayer {
    private String name;
    private Color color;
    private int famePoints;
    private int numDevelopers, numRiceTiles, numVillageTiles, numTwoSpaceTiles;
    private int extraActionTokens;
    private ArrayList<PalaceCard> palaceCards;
	
	public JavaPlayer(){

	}

    // this updates the fame points based on the additional points the player has earned
    public void updateFamePoints(int addFamePoints){
        this.famePoints += addFamePoints;
    }

    /*
    * Getters
    * */
    public String getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }

    public int getFamePoints(){
        return this.famePoints;
    }

    public int getNumDevelopers(){
        return this.numDevelopers;
    }

    public int getNumRiceTiles(){
        return this.numRiceTiles;
    }

    public int getNumVillageTiles(){
        return this.numVillageTiles;
    }

    public int getNumTwoSpaceTiles(){
        return this.numTwoSpaceTiles;
    }

    public int getExtraActionTokens(){
        return this.extraActionTokens;
    }

    public ArrayList<PalaceCard> getPalaceCards(){
        return this.palaceCards;
    }


}