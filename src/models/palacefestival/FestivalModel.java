package models.palacefestival;

import java.util.List;

public class FestivalModel {
    private FestivalPlayer[] players;
    private PalaceCard festivalCard;
    private int palaceValue;
    private int highestBid;

	public FestivalModel(FestivalPlayer[] p, PalaceCard fest, int pValue) {
        this.players = p;
        this.festivalCard = fest;
        this.palaceValue = pValue;
        this.highestBid = 0;
	}

    public List<FestivalPlayer> getWinners(){


    }

    public void calculateHighestBid(){

    }
}