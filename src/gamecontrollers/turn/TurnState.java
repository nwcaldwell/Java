package gamecontrollers.turn;



public abstract class TurnState {

    public TurnState(){

    }

    public boolean canEndTurn(){

        return false;
    }

    public Command endTurn(){

    }
}
