package models.palacefestival;

public class Symbol {
    int num;
	
	public Symbol(int number){
        this.num = number;
	}

    public int getSymbolNumber(){
        return this.num;
    }

    public boolean compare(Symbol symbol){
        if(this.num == symbol.getSymbolNumber())
            return true;
        return false;
    }

    public String toString(){
        return ""+num;
    }
}