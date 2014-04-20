package gamecontrollers;

import gamecontrollers.commandcreator.DevMoveController;
import gamecontrollers.commandcreator.TilePlacementController;
import gamecontrollers.palacefestival.FestivalController;
import gamecontrollers.palacefestival.FestivalTurnController;
import gamecontrollers.turn.HistoryChannelController;
import gamecontrollers.turn.PlanningModeCommandHandler;
import gamecontrollers.turn.ReplayController;
import gamecontrollers.turn.TurnController;
import models.Pair;
import models.board.*;
import models.board.Developer;
import models.board.Direction;
import models.board.Space;
import models.board.TileComponent;
import models.palacefestival.*;

import java.util.List;


public class Facade {

    private static Facade FacadeInstance = new Facade();
    private JavaGame game;
    private TilePlacementController tilePlacementController;
    private BoardLogicController boardLogicController;
    private FestivalController festivalController;
    private FestivalTurnController festivalTurnController;
    private HistoryChannelController historyChannelController;
    private DevMoveController devMoveController;
    private TurnController turnController;
    private ReplayController replayController;
    private PlanningModeCommandHandler planningModeCommandHandler;

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
        devMoveController = new DevMoveController();

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
      Board Communication Methods
    ========================================================================
    */

    public void placeTileComponent(TileComponent tileComponent) {
        throw new UnsupportedOperationException();
    }

    public void placeDeveloper(Developer direction, Space space){
        throw new UnsupportedOperationException();
    }

    public void moveTile(Direction direction){
        throw new UnsupportedOperationException();
    }

    public void moveDeveloper(Direction direction){
        throw new UnsupportedOperationException();
    }

    public void planCommand(){
        throw new UnsupportedOperationException();
    }

    public void endTurn() {
        throw new UnsupportedOperationException();
    }

    public void drawCardFromDeck() {
        throw new UnsupportedOperationException();
    }

    public void drawTheFestivalCard() {}

    public int findShortestPath(JavaPlayer p, Space origin, Space destination, List<Space> path) {

        System.out.println("Facade.findShortestPath is not implemented yet");
        return 0;
    }

    public boolean validPlacement(TileComponent tile, Space space){
        System.out.println("Facade.findShortestPath is not implemented yet");
        return false;
    }

    public void startPlacingTile(TileComponent tileComponent) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public void tabPalaceCard(){
        festivalTurnController.tabThroughPalaceCards();
    }

    public void playPalaceCard(){
        festivalTurnController.playPalaceCard();
    }

    public void dropOutOfFestival() {
//        FestivalController.dropCurrentPlayerFromFestival();
    }

    public void acceptTieRequest() {
        throw new UnsupportedOperationException();
    }

    public void askForPalaceFestivalTie(){
        throw new UnsupportedOperationException();
    }

    public void endFestival(){ throw new UnsupportedOperationException(); }


    // Actually execute the action being built
    // It returns a response that has messages for rules violation if any
    // if the action is executed successfully the response.hasErrors is set to true
    public Response doCommand(){
        throw new UnsupportedOperationException();

    }


}
