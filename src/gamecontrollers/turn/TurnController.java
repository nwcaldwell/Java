package gamecontrollers.turn;

import gamecontrollers.BoardLogicController;
import gamecontrollers.Response;
import gamecontrollers.commandcreator.GameplayCommandCreator;
import gamecontrollers.commands.gameplaycommands.DrawCardFromDeckCommand;
import gamecontrollers.commands.gameplaycommands.DrawFestivalCardCommand;
import gamecontrollers.commands.gameplaycommands.UseExtraActionTokenCommand;
import models.board.SharedResources;
import models.palacefestival.Deck;
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
    private SharedResources resources;
    private Deck deck;
    private BoardLogicController board;

    /*
  ========================================================================
      CONSTRUCTORS
  ========================================================================
   */
    public TurnController(GameplayCommandCreator gac, List<JavaPlayer> playerOrder, SharedResources resources, Deck deck, CommandHandler ch, BoardLogicController board){
        this.currentCommandCreator = gac;
        this.turnOrder = new ArrayList<JavaPlayer>(playerOrder);
        this.currentPlayer = turnOrder.get(0);
        this.commandHandler = ch;
        this.resources = resources;
        this.deck = deck;
        this.board = board;
        this.turnState = new NormalTurn(this);
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
        //actually dont do that
        //Response response = turnState.checkRules();
        Response response = new Response();

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

    public void setCommandBuilder(GameplayCommandCreator gameplayCommandCreator){
        currentCommandCreator = gameplayCommandCreator;
    }

    public SharedResources getSharedResources(){
        return resources;
    }

    public BoardLogicController getBoardLogicController(){
        return board;
    }

    public int getCurrentActionPoints(){
        return turnState.getActionPoints();
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
    public Response canEndTurn(){

        return turnState.canEndTurn();
    }

    public Response hasEnoughActionPoints(int i){
        return turnState.hasEnoughActionPoints(i);
    }

    public Response attemptToActionToken(){
        Response response = new Response(turnState.canPlayExtraActionToken().getMessages());
        if(!response.hasErrors()){
            commandHandler.handleCommand(new UseExtraActionTokenCommand(currentPlayer, this));
        }

        return response;

    }

    public Response attemptToDrawFromDeck(){
        Response response = turnState.canDrawCard();
        if(!response.hasErrors()){
            commandHandler.handleCommand(new DrawCardFromDeckCommand(currentPlayer, deck, this));
        }

        return response;
    }

    public Response attemptToDrawFestivalCard(){
        Response response = turnState.canDrawCard();
        if(!response.hasErrors()){
            commandHandler.handleCommand(new DrawFestivalCardCommand(currentPlayer, deck, this));
        }

        return response;
    }

     /*
   ========================================================================
       PUBLIC METHODS FOR TRACKING TURN STATE
   ========================================================================
    */

    public void playTile(){
        turnState.playTile();
    }
    public void removeTile(){
        turnState.removeTile();
    }
    public void playExtraActionToken(){
        turnState.playExtraActionToken();
    }
    public void returnExtraActionToken(){
        turnState.returnExtraActionToken();
    }
    public void drawCard(){
        turnState.drawCard();
    }
    public void returnCard(){
        turnState.returnCard();
    }

}
