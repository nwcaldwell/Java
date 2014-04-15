package models;

public class Deck {
    private Library library;
    private Graveyard graveyard;
    private PalaceCard festivalCard;
	
	public Deck(){

	}

    public void initPalaceCards(){

    }

    public PalaceCard drawFromDeck(){
        if(library.checkIfEmpty()){
            library.shuffle(graveyard.getDiscardedCards());
        }
        return library.draw();
    }

    public void discard(PalaceCard card){
        graveyard.discard(card);
    }

    public PalaceCard drawFestivalCard(){
        PalaceCard fest = festivalCard;
        festivalCard = library.draw();
        return fest;
    }

}