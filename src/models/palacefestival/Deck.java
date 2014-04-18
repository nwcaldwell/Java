package models.palacefestival;

import java.util.List;

public class Deck {
    private Library library;
    private Graveyard graveyard;
    private PalaceCard festivalCard;
	
	public Deck(){

	}


    /*
  ========================================================================
      MEMENTO METHODS
  ========================================================================
   */

    public DeckMemento createMemento(){
        return new DeckMemento(this.library, this.graveyard, this.festivalCard);
    }

    public void restoreFromMemento(DeckMemento oldDeck){
        this.library = new Library(oldDeck.getLibrary());
        this.graveyard = new Graveyard(oldDeck.getGraveyard());
        this.festivalCard = oldDeck.getFestivalCard();
    }

    /*
  ========================================================================
     OTHER METHODS
  ========================================================================
   */


    public void initPalaceCards(){

    }


    //Assuming that this will never be called without
    //checking deckEmpty()
    public PalaceCard drawFromDeck(){
        if(library.checkIfEmpty()){
            library.shuffle(graveyard.getDiscardedCards());
        }
        return library.draw();
    }

    //Check that needs to be called externally before asking for a card
    public boolean deckEmpty(){
        return library.checkIfEmpty();
    }

    //Method to get a copy of the library for undo
    public List<PalaceCard> getLibrary(){
        return library.getCards();
    }

    //method to get the copy of the graveyard for undo
    public List<PalaceCard> getGraveyard(){
        return graveyard.getDiscardedCards();
    }

    public void discard(PalaceCard card){
        graveyard.discard(card);
    }


    //this method does not check for an empty deck and must do this
    //this should probably done externally in the command
    //TODO implement check for empty deck that is undoable in command
    public PalaceCard drawFestivalCard(){
        PalaceCard fest = festivalCard;
        festivalCard = library.draw();
        return fest;
    }

    public void returnCard(PalaceCard card){
        library.returnCard(card);
    }

    public void returnFestivalCard(PalaceCard card){
        library.returnCard(festivalCard);
        this.festivalCard = card;
    }

}