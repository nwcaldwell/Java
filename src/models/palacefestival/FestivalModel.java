package models.palacefestival;

import java.util.ArrayList;

public class FestivalModel {
    private ArrayList<FestivalPlayer> players;
    private PalaceCard festivalCard;
    private int palaceValue;
    private int highestBid;

	public FestivalModel(ArrayList<FestivalPlayer> p, PalaceCard fest, int pValue) {
        this.players = p;
        this.festivalCard = fest;
        this.palaceValue = pValue;
        this.highestBid = 0;
	}

    public ArrayList<FestivalPlayer> getWinners(){
        ArrayList<FestivalPlayer> winners = new ArrayList<FestivalPlayer>();

        for (FestivalPlayer player : players){
            if((player.getBid() == highestBid) && !player.isDroppedOut()){
                winners.add(player);
            }
        }
        return winners;
    }

    public ArrayList<PalaceCard> getDiscardedCards(){
        ArrayList<PalaceCard> cardsToDiscard = new ArrayList<PalaceCard>();
        cardsToDiscard.add(festivalCard);
        for(FestivalPlayer player : players){
            cardsToDiscard.addAll(player.getDiscardedCards());
        }
        return cardsToDiscard;
    }


    public boolean compareBids(int playerBid){
        if(playerBid > highestBid){
            setHighestBid(playerBid);
            return true;
        }
        return false;
    }

    public void setHighestBid(int bid){
        highestBid = bid;
    }

    public void setPlayers(ArrayList<FestivalPlayer> p){
        players = p;
    }

    public void setFestivalCard(PalaceCard card){
        festivalCard = card;
    }

    public void setPalaceValue(int value){
        palaceValue = value;
    }

    public ArrayList<FestivalPlayer> getPlayers(){
        return players;
    }

    public PalaceCard getFestivalCard(){
        return festivalCard;
    }

    public int getPalaceValue(){
        return palaceValue;
    }

    public int getHighestBid(){
        return highestBid;
    }

}