package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ReplayController {

    private List<GameplayActionCommand> replayList;
    private ListIterator<GameplayActionCommand> iterator;

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
            iterator.previous().undo();
        }
    }

    public void goAll(){
        //iterate through the rest of the list and execute
        //DO NOT ITERATE THROUGH THE WHOLE LIST
        while(iterator.hasNext()){
            iterator.next().execute();
        }
    }

    public void setReplayList(List<GameplayActionCommand> replayListPassedIn){
        ArrayList<GameplayActionCommand> tempList = new ArrayList<GameplayActionCommand>();
        tempList.addAll(replayListPassedIn);
        //oops i forgot to undo everything lol
        //undo stuff

        //create iterator starting from the back of the passed in list
        ListIterator<GameplayActionCommand> it = tempList.listIterator(tempList.size()-1);
        //iterate backwards and undo while also adding to the replayList
        //this undoes all the commands in order and reverses the replayList
        while(it.hasPrevious()){
            GameplayActionCommand command = it.previous();
            command.undo();
            replayList.add(command);
        }

        iterator = replayList.listIterator();
    }
}
