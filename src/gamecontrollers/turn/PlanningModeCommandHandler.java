package gamecontrollers.turn;

//TODO [Kevin][Will]
import gamecontrollers.commands.GameplayActionCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PlanningModeCommandHandler implements CommandHandler {
    private List<GameplayActionCommand> plannedMoves;
    private HistoryChannelController history;

    private PlanningModeCommandHandler(HistoryChannelController history){
        plannedMoves = new ArrayList<GameplayActionCommand>();
        this.history = history;
    }

    //command has been planned, add to list of planned commands
    public void handleCommand(GameplayActionCommand c){
        plannedMoves.add(c);
    }

    public List<GameplayActionCommand> getPlannedMoves(){
        return plannedMoves;
    }

    //remove the last command that was planned
    public void cancelCommand(){
        plannedMoves.remove(plannedMoves.size()-1);
    }

    //remove the list of planned commands
    public void clearPlannedCommands(){
        plannedMoves.clear();
    }

    //execute and save all planned commmands
    public void executePlannedCommands(){
        for(GameplayActionCommand command :plannedMoves){
            command.execute();
            history.addCommand(command);
        }
    }
}
