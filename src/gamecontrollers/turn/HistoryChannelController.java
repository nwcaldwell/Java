package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;

import java.util.*;

import models.board.Game;
import models.palacefestival.JavaPlayer;


public class HistoryChannelController {
    //Store history as a list of turns where that turn
    //is a list of commands
    private ArrayList<ArrayList<GameplayActionCommand>> commandHistory;
    private int numPlayers;
    private int currentTurn = 0;

    public HistoryChannelController(int numPlayers){
        this.numPlayers = numPlayers;
        commandHistory = new ArrayList<ArrayList<GameplayActionCommand>>();
        commandHistory.add(new ArrayList<GameplayActionCommand>());
    }

    //Add the new command to the current turn list
    public void addCommand(GameplayActionCommand c){
        commandHistory.get(currentTurn).add(c);
    }

    //turn has ended, add a new turn list to the commandHistory
    //also increment the currentTurn index
    public void newTurn(){
        commandHistory.add(new ArrayList<GameplayActionCommand>());
        currentTurn++;
    }

    public List<GameplayActionCommand> getGameHistory(){
        ArrayList<GameplayActionCommand> completeHistory = new ArrayList<GameplayActionCommand>();


       for(ArrayList<GameplayActionCommand> list : commandHistory) {
           completeHistory.addAll(list);
       }

        return completeHistory;

    }

    public List<GameplayActionCommand> getLastRound(JavaPlayer p){
        //compile a list of all the turns in order

        ArrayList<GameplayActionCommand> lastRound = new ArrayList<GameplayActionCommand>();

        //get a ListIterator starting at the end
        ListIterator<ArrayList<GameplayActionCommand>> it = commandHistory.listIterator(currentTurn);


        for(int i = currentTurn - numPlayers - 1; i < commandHistory.size(); i++){
            //check that game has enough turns to go far enough back
            if(i >= 0)
                lastRound.addAll(commandHistory.get(i));
        }

        return lastRound;
    }


}
