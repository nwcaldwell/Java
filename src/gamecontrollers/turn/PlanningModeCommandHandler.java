package gamecontrollers.turn;

//TODO [Kevin][Will]
import gamecontrollers.commands.GameplayActionCommand;
import java.util.Stack;

public class PlanningModeCommandHandler implements CommandHandler {
    private Stack<GameplayActionCommand> plannedMoves;


    private PlanningModeCommandHandler(){

    }

    public void handleCommand(GameplayActionCommand c){
        plannedMoves.push(c);
    }

    public Stack<GameplayActionCommand> getPlannedMoves(){
        return plannedMoves;
    }

    public void cancelCommand(){
        plannedMoves.pop();
    }

    public void clearPlannedCommands(){
        plannedMoves.removeAllElements();
    }
}
