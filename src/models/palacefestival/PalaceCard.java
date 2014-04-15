package models.palacefestival;

import java.util.ArrayList;

public class PalaceCard {
    private ArrayList<Symbol> symbols;
	
	public PalaceCard(Symbol symbol){
        symbols = new ArrayList<Symbol>();
        symbols.add(symbol);
	}

    public PalaceCard(Symbol sym1, Symbol sym2){
        symbols = new ArrayList<Symbol>();
        symbols.add(sym1);
        symbols.add(sym2);
    }

    public ArrayList<Symbol> getSymbols(){
        return this.symbols;
    }

    //returns the number of symbols that the cards have in common
    public int compare(PalaceCard card){
        int points = 0;

        //for each symbol in the comparing card
        //compare to each symbol in this palace card
        //if they are the same, update the points
        for (Symbol cardSym : card.getSymbols()){
            for (Symbol thisSym : symbols){
                if(thisSym.compare(cardSym)){
                    points++;
                }
            }
        }

        return points;
    }
}