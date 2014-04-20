package models.palacefestival;

import java.util.ArrayList;
import java.util.List;

public class JavaPlayer {

    /*
    ========================================================================
      Instance variables
    ========================================================================
    */
    private String name;
    private String color;
    private int famePoints;
    private int numDevelopers, numRiceTiles, numVillageTiles, numTwoSpaceTiles;
    private int extraActionTokens;
    private List<PalaceCard> palaceCards;

    /*
    ========================================================================
      Constructor
    ========================================================================
    */
	public JavaPlayer(String playerName,
                      String  playerColor,
                      int initialFamePoints,
                      int initDeveloperCount,
                      int initialRiceCount,
                      int initialVillageCount,
                      int initialTwoSpaceCount,
                      int initialExtraActionTokenCount){
        name = playerName;
        color = playerColor;
        famePoints = initialFamePoints;
        numDevelopers = initDeveloperCount;
        numRiceTiles = initialRiceCount;
        numVillageTiles = initialVillageCount;
        numTwoSpaceTiles = initialTwoSpaceCount;
        extraActionTokens = initialExtraActionTokenCount;
        palaceCards = new ArrayList<PalaceCard>();
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

    public String getColor(){
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

    public List<PalaceCard> getPalaceCards(){
        return this.palaceCards;
    }

    public void drawCard(PalaceCard card) {
        palaceCards.add(card);
    }

    public void takeBackCard(PalaceCard card){
        palaceCards.remove(card);
    }

    public void useCard(PalaceCard card){
        palaceCards.remove(card);
    }
}