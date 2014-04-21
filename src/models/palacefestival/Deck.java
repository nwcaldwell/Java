package models.palacefestival;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private Library library;
    private Graveyard graveyard;
    private PalaceCard festivalCard;
	
	public Deck(){
        initLibrary();
        graveyard = new Graveyard();
        festivalCard = library.drawTopCard();
	}


    /*
  ========================================================================
      MEMENTO METHODS
  ========================================================================
   */

    public DeckMemento createMemento(){
        //make sure to never save an empty deck!
        checkEmpty();
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

    //Assuming that this will never be called without
    //checking deckEmpty()
    public PalaceCard drawFromDeck(){

        return library.drawTopCard();
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

    public PalaceCard getFestivalCard(){
        return festivalCard;
    }

    public void discard(List<PalaceCard> discardedCards) {
        for(PalaceCard card : discardedCards)
            graveyard.discard(card);
    }

    public void undoDiscard(List<PalaceCard> discardedCards) {
        graveyard.undoDiscardCards(discardedCards.size());
    }


    //this method does not check for an empty deck and must do this
    //this should probably done externally in the command
    //TODO implement check for empty deck that is undoable in command
    public PalaceCard drawFestivalCard(){
        PalaceCard fest = festivalCard;
        //not checking for empty, shouldnt be a problem
        festivalCard = library.drawTopCard();
        return fest;
    }

    public void returnCard(PalaceCard card){
        library.returnCard(card);
    }

    public void returnFestivalCard(PalaceCard card){
        library.returnCard(festivalCard);
        this.festivalCard = card;
    }

    /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */

    //checks if there are no cards in the library
    //if so fix that
    //Enforces that there will always be cards in the library to draw
    private void checkEmpty(){
        if(deckEmpty()){
            library.shuffle(graveyard.getDiscardedCards());
        }
    }



    public void initLibrary(){
        final List<PalaceCard> cards = new ArrayList<PalaceCard>() {{
            for (int i = 0; i < 5; i++) {
                add(new PalaceCard( new Symbol[] { new Symbol(1) } ) );
                add(new PalaceCard( new Symbol[] { new Symbol(2) } ) );
                add(new PalaceCard( new Symbol[] { new Symbol(3) } ) );
                add(new PalaceCard( new Symbol[] { new Symbol(1), new Symbol(2) } ) );
                add(new PalaceCard( new Symbol[] { new Symbol(1), new Symbol(3) } ) );
                add(new PalaceCard( new Symbol[] { new Symbol(2), new Symbol(3) } ) );
            }
        }};

        library = new Library(cards);
    }

}