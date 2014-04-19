package models.palacefestival;

import java.util.List;

/**
 * Created by kevinnieman on 4/18/14.
 */
public class DeckMemento {

    private Library library;
    private Graveyard graveyard;
    private PalaceCard festivalCard;

    public DeckMemento(){

    }

    public DeckMemento(Library l, Graveyard g, PalaceCard p){
        this.library = l;
        this.graveyard = g;
        this.festivalCard = p;
    }

    //Method to get a copy of the library for resaving memento
    public List<PalaceCard> getLibrary(){
        return library.getCards();
    }

    //method to get the copy of the graveyard for resaving memento
    public List<PalaceCard> getGraveyard(){
        return graveyard.getDiscardedCards();
    }

    //method to get festival card for resaving memento
    public PalaceCard getFestivalCard(){
        return this.festivalCard;
    }
}
