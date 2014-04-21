package models.palacefestival;

public class PalaceCard {
    private Symbol[] symbols;

    public PalaceCard(){
        symbols = new Symbol[0];
    }

	public PalaceCard(Symbol... symbol){
        symbols = symbol;

	}

    public Symbol[] getSymbols(){
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("palaceCard");

        for(Symbol sym : symbols){
            sb.append("_" + sym.toString());
        }
        return sb.toString();
    }
}