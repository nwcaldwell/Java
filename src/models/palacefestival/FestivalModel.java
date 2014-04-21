package models.palacefestival;

import java.util.ArrayList;
import java.util.List;

public class FestivalModel {
    private List<FestivalPlayer> players;
    private PalaceCard festivalCard;
    private int palaceValue;
    private int highestBid;

	public FestivalModel(List<FestivalPlayer> p, PalaceCard fest, int pValue) {
        this.players = p;
        this.festivalCard = fest;
        this.palaceValue = pValue;
        this.highestBid = 0;
	}

    public List<FestivalPlayer> getWinners(){
        List<FestivalPlayer> winners = new ArrayList<FestivalPlayer>();

        for (FestivalPlayer player : players){
            if((player.getBid() == highestBid) && !player.isDroppedOut()){
                winners.add(player);
            }
        }
        return winners;
    }

    private int numberOfPlayersLeft(){
        return getWinners().size();
    }

    public boolean onePlayerLeft(){
        return (numberOfPlayersLeft() == 1);
    }

    public boolean isTie(){
        if(numberOfPlayersLeft() == 1)
            return false;
        return true;
    }

    public boolean playersAreOutOfCards(){
        //see if the winners are out of cards. If so - the festival MUST end
        //but only end if all the players are out of cards.

        for(FestivalPlayer player : getWinners()){
            if(player.getCards().size() > 0){
                return false;
            }
        }
        return true;
    }

    public ArrayList<PalaceCard> getDiscardedCards(){
        ArrayList<PalaceCard> cardsToDiscard = new ArrayList<PalaceCard>();
        cardsToDiscard.add(festivalCard);
        for(FestivalPlayer player : players){
            cardsToDiscard.addAll(player.getDiscardedCards());
        }
        return cardsToDiscard;
    }

    public void compareBids(int playerBid){
        if(playerBid > highestBid){
            setHighestBid(playerBid);
        }
    }

    public void setHighestBid(int bid){
        highestBid = bid;
    }

    public void setPlayers(ArrayList<FestivalPlayer> p){
        players = p;
    }

    public List<FestivalPlayer> getPlayers(){
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

    public void addToPlayerBid(FestivalPlayer player, PalaceCard card) {
        player.addToBid(card.compare(festivalCard));
        compareBids(player.getBid());
    }

    public void undoAddToPlayerBid(FestivalPlayer player, PalaceCard card) {
        //TODO
    }
}