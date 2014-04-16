package gamecontrollers.turn;

import gamecontrollers.commandcreator.GameplayCommandCreator;
import models.palacefestival.JavaPlayer;
/**
 * //TODO [Will][Kevin]
 */
public class TurnController {
    //Command creator is in charge returning a command to do
    private GameplayCommandCreator currentCommandCreator;
    //Turn state encompasses all the state and logic required for a state
    private TurnState turnState;
    //This is the current player i guess
    private JavaPlayer currentPlayer;
    //Command handler is the one who will handle the commands from the gameplaycommandcreator
    private CommandHandler commandHandler;

    public TurnController(){

    }

    public void setCommandHandler(CommandHandler ch){
        this.commandHandler = ch;
    }

    public int getActionPointsLeft(){

        return 0;
    }

    public JavaPlayer getCurrentPlayer(){
        return new JavaPlayer();
    }

    public void commitMove(){}

    public void endTurn(){

    }


    public boolean canEndTurn(){

        return false;
    }

    public void setTurnState(TurnState ts){}

}
