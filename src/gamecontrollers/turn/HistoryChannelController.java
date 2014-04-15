package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;
import java.util.List;
import java.util.Stack;
import models.palacefestival.JavaPlayer;


public class HistoryChannelController {
    private Stack<GameplayActionCommand> commandHistory;
    private List<GameplayActionCommand> lastRound;

    public HistoryChannelController(){

    }

    public void addCommand(GameplayActionCommand c){

    }

    public Stack<GameplayActionCommand> getGameHistory(){
        // TODO implemente
        return null;

    }

    public List<GameplayActionCommand> getLastRound(JavaPlayer p){
        // TODO implemente
        return null;
    }
}
