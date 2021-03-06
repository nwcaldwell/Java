package view.screens.gameplay;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import models.board.JavaGame;
import models.palacefestival.JavaPlayer;
import view.View;
import view.ViewController;
import view.cgi.LWJGLBoardView;
import view.controls.BoardView;
import view.controls.ConsoleView;
import view.controls.PlayerView;
import view.controls.SharedResourcesView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

//TODO [Sydney][Jorge]

public abstract class GameplayView extends View {
    private static final int BORDER = 10;
    protected ConsoleView consoleView;
    protected ArrayList<PlayerView> playerViews;
    protected BoardView boardView;
    protected SharedResourcesView sharedResourcesView;
    protected JPanel playerContainer;
    private JavaGame game;

    protected GameplayView(ViewController viewC) {
        super(viewC);
    }

    //this method sets up the default layout for the board
    public void init(){
        game = Facade.getInstance().getGame();
        //create the attributes
        consoleView = new ConsoleView();
        playerViews = new ArrayList<PlayerView>();
        boardView = new LWJGLBoardView(game.getBoard(), getViewController());
        getViewController().setBoardview(boardView);
        sharedResourcesView = new SharedResourcesView();

        //setup view
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        //this is the left side of the screen, needs to somehow conform to the size of the screen
        JPanel rightSide = new JPanel();
        rightSide.setPreferredSize(new Dimension(this.getScreenWidth() / 4 - BORDER / 2, this.getScreenHeight() - BORDER * 2));
        rightSide.setBackground(Color.WHITE);

        //the shared resources and the console are on the left panel. add them in the respective order
        rightSide.add(sharedResourcesView);
        rightSide.add(consoleView);
        add(rightSide, BorderLayout.EAST);

        //the player views need to have something to encapsulate them all
        playerContainer = new JPanel();
        playerContainer.setMinimumSize(new Dimension(3*this.getScreenWidth()/4 - BORDER/2, this.getScreenHeight()/4- BORDER));

        for(int i = 0; i < game.getPlayers().length; i++){
            PlayerView player = new PlayerView(game.getPlayers()[i].getColor());
            this.addPlayerView(player);
        }

        //this includes the BoardView and the Player Views
        JPanel leftSide = new JPanel();
        leftSide.setPreferredSize(new Dimension(3 * this.getScreenWidth() / 4 - BORDER / 2, this.getScreenHeight() - BORDER * 2));
        leftSide.setBackground(Color.WHITE);
        int boardViewHeight = this.getScreenHeight() - 250 - BORDER*2;
        boardView.setPreferredSize(new Dimension(3*this.getScreenWidth()/4 - BORDER/2, boardViewHeight));
        boardView.setSize(new Dimension(3*this.getScreenWidth()/4 - BORDER/2, boardViewHeight));
        boardView.setFocusable(false);
        leftSide.add(boardView);
        leftSide.add(playerContainer);

        add(leftSide, BorderLayout.WEST);

    }

    protected void addPlayerView(PlayerView player) {
        //add the playerview to the player array list
        playerViews.add( player );

        //add the player to the view in order to display
        playerContainer.add(player);
    }


    @Override
    public void displayResponseToConsole(Response response) {
        consoleView.displayMessage(response);
    }

    public void update(){
        boardView.update();
        sharedResourcesView.update(game.getSharedResources(), game.getDeck());
        consoleView.update();

        JavaPlayer[] players = game.getPlayers();
        JavaPlayer currentPlayer = Facade.getInstance().getCurrentPlayer();
        for(int i = 0; i < players.length; i++){
            playerViews.get(i).update(players[i], players[i].equals(currentPlayer));
        }
        getViewController().setFrameAsFocused();
    }

    public BoardView getBoardView(){
        return boardView;
    }

}
