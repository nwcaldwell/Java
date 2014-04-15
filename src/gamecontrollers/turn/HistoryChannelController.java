package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;
import models.board.Player;
import java.util.List;
import java.util.Stack;

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

    public List<GameplayActionCommand> getLastRound(Player p){
        // TODO implemente
        return null;
    }
}
