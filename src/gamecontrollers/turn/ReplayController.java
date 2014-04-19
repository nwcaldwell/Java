package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ReplayController {
    private List<GameplayActionCommand> replayList;
    ListIterator<GameplayActionCommand> iterator;

    public ReplayController(){
        replayList = new ArrayList<GameplayActionCommand>();
    }

    public void goForward(){
        if(iterator.hasNext()){
            iterator.next().execute();
        }
    }

    public void goBackward(){
        if(iterator.hasPrevious()){
            iterator.previous().execute();
        }
    }

    public void goAll(){
        for(GameplayActionCommand command : replayList){
            command.execute();
        }
    }

    public void setReplayList(List<GameplayActionCommand> replayList){
        this.replayList.addAll(replayList);
        iterator = replayList.listIterator();

    }
}
