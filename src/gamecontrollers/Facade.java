package gamecontrollers;

import gamecontrollers.commandcreator.*;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.palacefestival.FestivalTurnController;
import gamecontrollers.turn.*;
import models.Pair;
import models.board.Direction;
import models.board.JavaGame;
import models.board.Space;
import models.board.TileComponent;
import models.board.TileComponentContents.Palace;
import models.palacefestival.FestivalModel;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.lang.reflect.Array;
import java.util.Arrays;
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
    private PlayModeCommandHandler playModeCommandHandler;
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
        tilePlacementCommandCreator = new TilePlacementCommandCreator(turnController, game.getSharedResources());
        //this new creator for turn controller is weird
        //gotta give him an arbitrary turnstate?
        planningModeCommandHandler = new PlanningModeCommandHandler(historyChannelController);
        playModeCommandHandler = new PlayModeCommandHandler(historyChannelController);
        turnController = new TurnController(tilePlacementCommandCreator, Arrays.asList(game.getPlayers()), game.getSharedResources(), playModeCommandHandler, boardLogicController);
        replayController = new ReplayController();

        palaceCommandCreator = new MaskPalaceCommandCreator(turnController, game.getSharedResources());

    }

    /*
    ========================================================================
      GETTERS
    ========================================================================
    */

    public JavaGame getGame() {
        return game;
    }

    public FestivalModel getFestivalModel() {
        return festivalTurnController.getFestivalModel();
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

    public void startPlacingTile(TileComponent tileComponent) {
        tilePlacementCommandCreator.setCurrentSpace(game.getBoard().getRoot());
        tilePlacementCommandCreator.setCurrentTileComponent(tileComponent);
        turnController.setCommandBuilder(tilePlacementCommandCreator);
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
        tilePlacementCommandCreator.move(direction);
    }

    public void movePalace(Direction direction){
        palaceCommandCreator.move(direction);
    }

    public void moveDeveloper(Direction direction){
        developerMovementCommandCreator.move(direction);
    }

    public void endTurn() {
        turnController.endTurn();

    }

    public void tabThroughPalace() {
        palaceCommandCreator.tabThroughPalacesRemaining();
    }

    public void playExtraActionToken() {
        turnController.attemptToActionToken();
    }

    public void rotateCurrentTileComponent() {
        tilePlacementCommandCreator.rotateCurrentTileComponent();
    }


    /*
    ========================================================================
      Festival Communication Methods
    ========================================================================
    */


    public void startFestival(JavaPlayer[] players, PalaceCard festivalCard, Palace palaceAssociated){
        festivalController.startFestival(players, festivalCard, palaceAssociated);
    }

    public void tabPalaceCard(){
        festivalTurnController.tabThroughPalaceCards();
    }

    public void playPalaceCard(){
        festivalTurnController.playPalaceCard();
    }

    public void dropOutOfFestival() {
        festivalTurnController.dropOutCommandCreator();
    }

    public void endFestivalTurn(){
        festivalTurnController.endTurnFinalization();
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




    public void endFestival(List<PalaceCard> discardedCards, List<JavaPlayer> playersFromFestival, int pointsEarned) {
        //need to go to the viewController, and go back to the board view
        //then apply this to the game
        game.endFestival(discardedCards, playersFromFestival, pointsEarned);

    }

    public void undoEndFestival(List<PalaceCard> discardedCards, List<JavaPlayer> playersFromFestival, int pointsEarned) {
        game.undoFestival(discardedCards, playersFromFestival, pointsEarned);
    }

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

    public void startPlanningMode(){
        turnController.setCommandHandler(planningModeCommandHandler);
    }

    public void startPlayMode(){
        turnController.setCommandHandler(playModeCommandHandler);
    }

    public void undoCommand(){
        planningModeCommandHandler.cancelCommand();
    }

}
