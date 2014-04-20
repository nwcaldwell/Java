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

    /* Festival Player Actions
     * Higher level of abstraction */
    public void playCard(PalaceCard card){
        removeCardFromPlayer(card);
        removeCardFromEligibleCards(card);
        discardCard(card);
    }

    public void undoPlayCard(PalaceCard card){
        undoRemoveCardFromPlayer(card);
        undoRemoveCardFromEligibleCards(card);
        undoDiscardCard(card);
    }

    //lower abstraction level methods

    public PalaceCard getCardAtIndex(int index){
        return cards.get(index);
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

    private void removeCardFromPlayer(PalaceCard card){
        player.useCard(card);
    }

    private void undoRemoveCardFromPlayer(PalaceCard card){
        player.drawCard(card);
    }

    private void removeCardFromEligibleCards(PalaceCard card){
        cards.remove(card);
    }

    private void undoRemoveCardFromEligibleCards(PalaceCard card){
        cards.add(card);
    }

    private void discardCard(PalaceCard card){
        discardedCards.add(card);
    }

    private void undoDiscardCard(PalaceCard card){
        discardedCards.remove(card);
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

    public ArrayList<PalaceCard> getCards(){
        return cards;
    }

}