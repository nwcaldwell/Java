package models.palacefestival;

import java.util.ArrayList;
import java.util.List;

public class FestivalModel {
    private List<FestivalPlayer> players;
    private List<FestivalPlayer> turnOrder;
    private PalaceCard festivalCard;
    private int palaceValue;
    private int highestBid;

	public FestivalModel(List<FestivalPlayer> p, PalaceCard fest, int pValue) {
        this.players = p;
        turnOrder = new ArrayList<FestivalPlayer>(players.size());
        for(FestivalPlayer player : players){
            turnOrder.add(player);
        }
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


    public ArrayList<PalaceCard> getDiscardedCards(){
        ArrayList<PalaceCard> cardsToDiscard = new ArrayList<PalaceCard>();
        cardsToDiscard.add(festivalCard);
        for(FestivalPlayer player : players){
            cardsToDiscard.addAll(player.getDiscardedCards());
        }
        return cardsToDiscard;
    }

    /*
    ========================================================================
      End Festival Checks
    ========================================================================
    */

    private int numberOfPlayersLeft(){
        return getWinners().size();
    }

    private boolean onePlayerLeft(){
        return (numberOfPlayersLeft() == 1);
    }

    private boolean isTie(){
        if(numberOfPlayersLeft() == 1)
            return false;
        return true;
    }

    private boolean playersAreOutOfCards(){
        //see if the winners are out of cards. If so - the festival MUST end
        //but only end if all the players are out of cards.

        for(FestivalPlayer player : getWinners()){
            if(player.getCards().size() > 0){
                return false;
            }
        }
        return true;
    }

    public boolean canStartNewRound(){
        return playersAreOutOfCards() || onePlayerLeft();
    }

    /*
    ========================================================================
      Bid Methods
    ========================================================================
    */

    public void addToPlayerBid(FestivalPlayer player, PalaceCard card) {
        player.addToBid(card.compare(festivalCard));
        compareBids(player.getBid());
    }

    public void undoAddToPlayerBid(FestivalPlayer player, PalaceCard card) {
        //TODO
        player.undoAddToBid(card.compare(festivalCard));
        calculateHighestBid();
    }

    private void calculateHighestBid() {
        int bid = 0;
        for(FestivalPlayer player : players){
            if(player.getBid() > bid)
                bid = player.getBid();
        }

        setHighestBid(bid);
    }

    public void compareBids(int playerBid){
        if(playerBid > highestBid){
            setHighestBid(playerBid);
        }
    }

    /*
    ========================================================================
      Setters
    ========================================================================
    */

    public void setHighestBid(int bid){
        highestBid = bid;
    }

    public void setPlayers(ArrayList<FestivalPlayer> p){
        players = p;
    }

    /*
    ========================================================================
      Getters
    ========================================================================
    */

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

    public List<FestivalPlayer> getTurnOrder(){
        return turnOrder;
    }
}