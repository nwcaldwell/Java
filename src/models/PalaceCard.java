package models;

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

    //returns the number of symbols that the cards have in common
    public int compare(PalaceCard card){
        int points = 0;

        return points;
    }
}