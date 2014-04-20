package gamecontrollers;

import gamecontrollers.commandcreator.DevMoveController;
import gamecontrollers.commandcreator.TilePlacementController;
import gamecontrollers.palacefestival.FestivalController;
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
import models.palacefestival.Deck;
import models.palacefestival.FestivalPlayer;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.List;


public class Facade {

    private static Facade FacadeInstance = new Facade();
    private JavaGame game;
    private TilePlacementController tilePlacementController;
    private BoardLogicController boardLogicController;
    private FestivalController festivalController;
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
    public void startGame(List<Pair<String,String>> playersData){
        game = new JavaGame(playersData);
    }

    // getters


    public static Board getBoard(){
        throw new UnsupportedOperationException();
    }

    public JavaPlayer[] getPlayer(int indexOfPlayer){
        throw new UnsupportedOperationException();
    }

    public SharedResources getSharedResources(){
        throw new UnsupportedOperationException();
    }

    public Deck getDeck(){
        throw new UnsupportedOperationException();
    }

    public FestivalPlayer getFestivalPlayer(int indexOfPlayer){
//        return festivalController.getPlayers();
        throw new UnsupportedOperationException();
    }

    // regular game methods
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

    // Festival Methods
    public void drawCardFromDeck() {
        throw new UnsupportedOperationException();
    }

    public void drawTheFestivalCard() {}


    public void dropOutOfFestival() {
        throw new UnsupportedOperationException();
    }

    public void acceptTieRequest() {
        throw new UnsupportedOperationException();
    }


    public boolean validPlacement(TileComponent tile, Space space){
        System.out.println("Facade.findShortestPath is not implemented yet");
        return false;
    }

    public void startPlacingTile(TileComponent tileComponent) {
        throw new UnsupportedOperationException();
    }

    // Festival Methods
    public void startFestival() {
        throw new UnsupportedOperationException();
    }

    public void askForPalaceFestivalTie(){
        throw new UnsupportedOperationException();
    }

    public void startNewFestival(JavaPlayer[] players, PalaceCard festivalCard, Space palaceAssociated){
        //tODO
//        festivalController.startFestival(players, festivalCard, palaceAssociated);
    }

    public void endFestival(){ throw new UnsupportedOperationException(); }

    public int findShortestPath(JavaPlayer p, Space origin, Space destination, List<Space> path) {

        System.out.println("Facade.findShortestPath is not implemented yet");
        return 0;
    }

    // Actually execute the action being built
    // It returns a response that has messages for rules violation if any
    // if the action is executed successfully the response.hasErrors is set to true
    public Response doCommand(){
        throw new UnsupportedOperationException();

    }
    //Starting a new game
    public void beginNewGame(String[] playerNames, String[] playerColors){
        throw new UnsupportedOperationException();
    }

}
