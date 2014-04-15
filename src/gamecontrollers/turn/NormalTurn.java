package gamecontrollers.turn;


import view.commands.Command;

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
