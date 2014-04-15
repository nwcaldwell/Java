package gamecontrollers.turn;


public class NormalTurn extends TurnState{
    private TurnController turnController;
    private TurnState otherState;

    public NormalTurn(){

    }

    public boolean canEndTurn(){
        return false;
    }

    public Command endTurn(){

    }
}
