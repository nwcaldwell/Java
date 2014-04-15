package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;

public class NormalTurn extends TurnState{
    private TurnController turnController;
    private TurnState otherState;

    public NormalTurn(){

    }

    public boolean canEndTurn(){
        return false;
    }

    public EndTurnCommand endTurn(){
        // TODO implement
        return null;
    }
}
