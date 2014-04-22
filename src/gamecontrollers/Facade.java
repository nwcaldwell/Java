package gamecontrollers;

import gamecontrollers.commandcreator.*;
import gamecontrollers.commands.gameplaycommands.DealCardsCommand;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.palacefestival.FestivalTurnController;
import gamecontrollers.turn.*;
import models.Pair;
import models.board.*;
import models.board.TileComponentContents.Palace;
import models.palacefestival.FestivalModel;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

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

        palaceCommandCreator = new MaskPalaceCommandCreator(turnController, game.getSharedResources());
        //this new creator for turn controller is weird
        //gotta give him an arbitrary turnstate?
        planningModeCommandHandler = new PlanningModeCommandHandler(historyChannelController);
        playModeCommandHandler = new PlayModeCommandHandler(historyChannelController);
        turnController = new TurnController(tilePlacementCommandCreator, Arrays.asList(game.getPlayers()), game.getSharedResources(), game.getDeck(), playModeCommandHandler, boardLogicController);

        //lol oops
        developerMovementCommandCreator.setTurnController(turnController);
        tilePlacementCommandCreator.setTurnController(turnController);
        palaceCommandCreator.setTurnController(turnController);

        replayController = new ReplayController();

        turnController.startGame();
    }

    /*
    ========================================================================
      GETTERS, pretty much for updating the views
    ========================================================================
    */

    public JavaGame getGame() {
        return game;
    }

    public FestivalModel getFestivalModel() {
        return festivalTurnController.getFestivalModel();
    }

    public JavaPlayer getCurrentPlayer(){
        return turnController.getCurrentPlayer();
    }

    public int getCurrentPlayerActionPoints(){
        return turnController.getCurrentActionPoints();
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
        //TODO throws an index out of bounds exception, run and press I, V, R, P, 2 and you'll see
        System.out.println("Facade - beginning tile placement methods");
        System.out.println("Setting the root");
        tilePlacementCommandCreator.setCurrentSpace(game.getBoard().getRoot());
        System.out.println("Setting the tile component to place");
        tilePlacementCommandCreator.setCurrentTileComponent(tileComponent);
        System.out.println("setting the command builder");
        turnController.setCommandBuilder(tilePlacementCommandCreator);
        System.out.println("completed placing tile");
    }

    /*
    ========================================================================
      Turn Handlers
    ========================================================================
    */

    public Response endTurn() {
        return turnController.attemptToEndTurn();
    }

    public Response askForFestival(){
        //TODO fix this, placeholder for functionality example
        return turnController.attemptToHoldFestival(null);
    }

    /*
    ========================================================================
      Board Communication Methods
    ========================================================================
    */
    public List<Direction> getTilePlacementPath(){
    	return tilePlacementCommandCreator.getPath();
    }
    
    public TileComponent getCurrentTileComponent(){
    	return tilePlacementCommandCreator.getCurrentTile();
    }

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

    public void tabThroughPalace() {
        palaceCommandCreator.tabThroughPalacesRemaining();
    }

    public boolean validPlacement(TileComponent tile, Space space){
        System.out.println("Facade.findShortestPath is not implemented yet");
        return false;
    }

    public void rotateCurrentTileComponent() {
        tilePlacementCommandCreator.rotateCurrentTileComponent();
    }

    /*
    ========================================================================
      Shared Resources communication
    ========================================================================
    */

    public Response playExtraActionToken() {
        return turnController.attemptToActionToken();
    }

    public Response drawCardFromDeck() {
        return turnController.attemptToDrawFromDeck();
    }

    public Response drawTheFestivalCard() {
        return turnController.attemptToDrawFestivalCard();
    }

    /*
    ========================================================================
      Festival Communication Methods
    ========================================================================
    */

    public void startFestival(List<JavaPlayer> players, PalaceCard festivalCard, Palace palaceAssociated){
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

    public Response endFestivalTurn(){
        return festivalTurnController.attemptToEndTurn();
    }

    public void askForPalaceFestivalTie() {
        festivalTurnController.endFestival();
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

    public void cancelCurrentCommand(){
        throw new UnsupportedOperationException();
    }

}
