package gamecontrollers.turn;


import view.commands.Command;

public abstract class TurnState {

    public TurnState(){

    }

    public boolean canEndTurn(){

        return false;
    }

    public Command endTurn(){

    }
}
