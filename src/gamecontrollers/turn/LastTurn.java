package gamecontrollers.turn;


import models.board.Player;

public class LastTurn extends TurnState {

    private Player firstLastTurn;
    private FinalScoreCalculator scoreCalculator;
    private TurnController turnController;
    private TurnState otherState;

    public LastTurn(){

    }

    public boolean canEndTurn(){
        return false;
    }

    public Command endTurn(){

    }
}
