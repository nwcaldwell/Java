package models.palacefestival;

import java.util.ArrayList;

public class FestivalPlayer {
    private JavaPlayer player;
    private ArrayList<PalaceCard> cards;
    private ArrayList<PalaceCard> discardedCards;
    private boolean droppedOut;
    private boolean hasPlayedThisRound;
    private int bid;

	public FestivalPlayer(JavaPlayer p, ArrayList<PalaceCard> palaceCards) {
        this.player = p;
        this.cards = palaceCards;
        this.discardedCards = new ArrayList<PalaceCard>();
        this.droppedOut = false;
        this.hasPlayedThisRound = false;
        this.bid = 0;
	}

    /* GETTERS */
    public JavaPlayer getPlayer(){
        return player;
    }

    public boolean isDroppedOut() {
        return droppedOut;
    }

    public boolean hasPlayedThisRound() {
        return hasPlayedThisRound;
    }

    public int getBid() {
        return bid;
    }

    public ArrayList<PalaceCard> getDiscardedCards() {
        return discardedCards;
    }


    /* Festival Player Actions */
    public void discardCard(PalaceCard card){
        discardedCards.add(card);
    }

    public PalaceCard getCardAtIndex(int index){
        return cards.get(index);
    }

    public PalaceCard useCardAtindex(int index){
        PalaceCard card = cards.get(index);
        cards.remove(index);
        return card;
    }

    public void dropOutOfFestival() {
        droppedOut = true;
    }

    public void addToBid(int points){
        bid += points;
    }

    public void endTurn(){
        hasPlayedThisRound = true;
    }

    public void startNewRound(){
        hasPlayedThisRound = false;
    }

}