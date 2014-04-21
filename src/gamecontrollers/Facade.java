package gamecontrollers;

import gamecontrollers.commandcreator.DeveloperMovementCommandCreator;
import gamecontrollers.commandcreator.PalaceCommandCreator;
import gamecontrollers.commandcreator.TilePlacementCommandCreator;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.palacefestival.FestivalTurnController;
import gamecontrollers.turn.HistoryChannelController;
import gamecontrollers.turn.PlanningModeCommandHandler;
import gamecontrollers.turn.ReplayController;
import gamecontrollers.turn.TurnController;
import models.Pair;
import models.board.Direction;
import models.board.JavaGame;
import models.board.Space;
import models.board.TileComponent;
import models.palacefestival.FestivalModel;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.List;


public class Facade {

    private static Facade FacadeInstance = new Facade();
    private JavaGame game;
    private TilePlacementCommandCreator tilePlacementCommandCreator;
    private BoardLogicController boardLogicController;
    private FestivalController festivalController;
    private FestivalTurnController festivalTurnController;
    private HistoryChannelController historyChannelController;
    private DeveloperMovementCommandCreator developerMovementCommandCreator;
    private TurnController turnController;
    private ReplayController replayController;
    private PlanningModeCommandHandler planningModeCommandHandler;
    private PalaceCommandCreator palaceCommandCreator;

    public static Facade getInstance() {
        return FacadeInstance;
    }

    private Facade() {

    }

    // start
    public void startGame(List<Pair<String,String>> playersData, String boardFile){
        game = new JavaGame(playersData, boardFile);
        historyChannelController = new HistoryChannelController(game.getPlayers().length + 1);
        boardLogicController = new BoardLogicController(game.getBoard());
        festivalController = new FestivalController(historyChannelController);
        festivalTurnController = festivalController.getTurnController();
        developerMovementCommandCreator = new DeveloperMovementCommandCreator(turnController, boardLogicController);
    }

    /*
    ========================================================================
      GETTERS
    ========================================================================
    */

    public JavaGame getGame() {
        return game;
    }

    public FestivalModel getFestival() {
        throw new UnsupportedOperationException();
    }

    /*
    ========================================================================
      Setup for command builders methods
    ========================================================================
    */
    public Response setupForMovingDeveloper(){
        Response response = developerMovementCommandCreator.setCurrentDeveloper();

        if ( !response.hasErrors() ) {
              turnController.setCommandBuilder(developerMovementCommandCreator);
        }

        return response;
    }

    /*
    ========================================================================
      Board Communication Methods
    ========================================================================
    */
    public void tabThroughDevelopers() {
        developerMovementCommandCreator.iterateThroughBoardDevelopers();
    }

    public void moveTile(Direction direction){
        throw new UnsupportedOperationException();
    }

    public void moveDeveloper(Direction direction){
        throw new UnsupportedOperationException();
    }

    public void endTurn() {
        throw new UnsupportedOperationException();
    }

    public void tabThroughPalace() {
        palaceCommandCreator.tabThroughPalacesRemaining();
    }

    public void playExtraActionToken() {
        turnController.playExtraActionToken();
    }

    public void rotateCurrentTileComponent() {
        tilePlacementCommandCreator.rotateCurrentTileComponent();
    }


    /*
    ========================================================================
      Festival Communication Methods
    ========================================================================
    */
    public void startNewFestival(JavaPlayer[] players, PalaceCard festivalCard, Space palaceAssociated){
        //TODO ?
//        festivalController.startFestival(players, festivalCard, palaceAssociated);
    }

    public void startFestival() {
        // festivalController.startFestival(gam);
    }

    public void tabPalaceCard(){
        festivalTurnController.tabThroughPalaceCards();
    }

    public void playPalaceCard(){
        festivalTurnController.playPalaceCard();
    }

    public void dropOutOfFestival() {
        festivalTurnController.dropOut();
    }

    public void endFestivalTurn(){
        festivalTurnController.endTurn();
    }

    public void acceptTieRequest() {
        throw new UnsupportedOperationException();
    }

    public void askForPalaceFestivalTie(){
        throw new UnsupportedOperationException();
    }

    public void drawCardFromDeck() {
        throw new UnsupportedOperationException();
    }

    public void drawTheFestivalCard() {}

    public boolean validPlacement(TileComponent tile, Space space){
        System.out.println("Facade.findShortestPath is not implemented yet");
        return false;
    }

    public void startPlacingTile(TileComponent tileComponent) {
        throw new UnsupportedOperationException();
    }


    public void endFestival(){ throw new UnsupportedOperationException(); }


    /*
    ========================================================================
      Perform actions methods
    ========================================================================
    */
    // Actually execute the action being built
    // It returns a response that has messages for rules violation if any
    // if the action is executed successfully the response.hasErrors is set to true
    public Response commitMove(){
        return turnController.commitMove();
    }

    public void planCommand(){
        throw new UnsupportedOperationException();
    }



}
