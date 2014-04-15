package gamecontrollers.turn;


import view.commands.Command;

public class PlanningController {
    private Stack<GameplayActionCommand> plannedMoves;
    private TurnState turnState;

    private PlanningController(){

    }

    public Stack<GameplayActionCommand> getPlannedMoves(){
        return plannedMoves;
    }

    public void planCommand(Command c){

    }

    public void cancelCommand(){

    }

    public void clearPlannedCommands(){

    }
}
