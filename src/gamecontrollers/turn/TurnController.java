package gamecontrollers.turn;

import gamecontrollers.Response;
import gamecontrollers.commandcreator.GameplayCommandCreator;
import gamecontrollers.rules.Rule;
import gamecontrollers.rules.turnrules.TurnRule;
import models.palacefestival.JavaPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO [Will][Kevin]
 */
public class TurnController {

    //Command creator is in charge returning a command to do
    private GameplayCommandCreator currentCommandCreator;
    //Command handler is the one who will handle the commands from the gameplaycommandcreator
    private CommandHandler commandHandler;
    //Turn state encompasses all the state and logic required for a state
    private TurnState turnState;


    //This is the current player i guess
    private JavaPlayer currentPlayer;
    //This is the order of players in the turn
    private ArrayList<JavaPlayer> turnOrder;




    /*
  ========================================================================
      CONSTRUCTORS
  ========================================================================
   */
    public TurnController(GameplayCommandCreator gac, TurnState ts, ArrayList<JavaPlayer> playerOrder, CommandHandler ch){
        this.currentCommandCreator = gac;
        this.turnState = ts;
        this.turnOrder = playerOrder;
        this.currentPlayer = turnOrder.get(0);
        this.commandHandler = ch;
    }

    /*
  ========================================================================
      GETTERS AND SETTERS
  ========================================================================
   */
    public void setCommandHandler(CommandHandler ch){
        this.commandHandler = ch;
    }

    //Facade has said to do command
    //Response incase of error
    public Response commitMove(){
        //check this turns rules stuff real quick
        Response response = turnState.checkRules();


        //check from CommandCreator if it is possible and add it to current response
        response.addMessages(currentCommandCreator.checkPossible().getMessages());


        if(!response.hasErrors()){
            //Command is possible so fetch it and have it handled
            commandHandler.handleCommand(currentCommandCreator.getCommand());
        }
        return response;
    }


    //Return the current player
    public JavaPlayer getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setTurnState(TurnState ts){
        this.turnState = ts;
    }

    public int getCost(){
        return currentCommandCreator.getCost();
    }



     /*
   ========================================================================
       PUBLIC METHODS
   ========================================================================
    */


    /*
        End this players turn, make the currentPlayer the next player in the list
     */
    public void endTurn(){

        int index = turnOrder.indexOf(currentPlayer);

        currentPlayer = turnOrder.get( (index+1) % turnOrder.size() );
    }

    /*
        Rewind turn for purpose of undo
     */
    public void revertTurn(){
        int index = turnOrder.indexOf(currentPlayer);

        currentPlayer = turnOrder.get( (index-1) % turnOrder.size() );
    }

    /*
        This functionality has been redirected to TurnPhase
        I am unsure whether or not to delete this or route it into TurnPhase
        as it may be unnecessary now
     */
    public boolean canEndTurn(){

        return turnState.canEndTurn();
    }

    public boolean hasEnoughActionPoints(int i){
        return turnState.hasEnoughActionPoints(i);
    }


     /*
   ========================================================================
       PRIVATE METHODS
   ========================================================================
    */


}
