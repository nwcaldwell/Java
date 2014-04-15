package gamecontrollers.turn;


import gamecontrollers.commands.GameplayActionCommand;
import java.util.Stack;

public class PlanningController {
    private Stack<GameplayActionCommand> plannedMoves;
    private TurnState turnState;

    private PlanningController(){

    }

    public Stack<GameplayActionCommand> getPlannedMoves(){
        return plannedMoves;
    }

    public void planCommand(GameplayActionCommand c){

    }

    public void cancelCommand(){

    }

    public void clearPlannedCommands(){

    }
}
