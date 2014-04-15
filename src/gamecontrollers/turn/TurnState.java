package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;

public abstract class TurnState {

    public TurnState(){

    }

    public boolean canEndTurn(){

        return false;
    }

    public EndTurnCommand endTurn(){
        // TODO implement
        return null;
    }
}
