package gamecontrollers.turn;

import gamecontrollers.commands.gameplaycommands.EndTurnCommand;
import models.palacefestival.JavaPlayer;
public class LastTurn extends TurnState {

    private JavaPlayer firstLastTurn;
    private FinalScoreCalculator scoreCalculator;
    private TurnController turnController;
    private TurnState otherState;

    public LastTurn(){

    }

    public boolean canEndTurn(){
        return false;
    }

    public EndTurnCommand endTurn(){
        // TODO implement
        return null;
    }
}
