package view.screens.gameplay;

import view.*;
import view.controls.ConsoleView;
import view.controls.BoardView;
import view.controls.PlayerView;
import view.controls.SharedResourcesView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameplayView extends View {

    private ConsoleView consoleView;
    private ArrayList<PlayerView> playerViews;
    private BoardView boardView;
    private SharedResourcesView sharedResourcesView;
    JPanel playerContainer;

    protected GameplayView(ViewController viewC, MediaController mediaC) {
        super(viewC, mediaC);

        //create the attributes
        consoleView = new ConsoleView();
        playerViews = new ArrayList<PlayerView>();
        boardView = new BoardView();
        sharedResourcesView = new SharedResourcesView();

        //setup view sizes
        setMaximumSize(new Dimension(500, 500)); //TODO what these numbers are and where to find them?
        setBorder(new EmptyBorder(25, 25, 25, 25)); //TODO save these numbers somewhere

        //initialize everything else
        initializeView();
    }

    //this method sets up the default layout for the board
    private void initializeView(){
        //this is the left side of the screen, needs to somehow conform to the size of the screen
        JPanel leftSide = new JPanel();
        leftSide.setMinimumSize(new Dimension(500, 500));

        //the shared resources and the console are on the left panel. add them in the respective order
        leftSide.add(sharedResourcesView);
        leftSide.add(consoleView);
        add(leftSide, BorderLayout.WEST);

        //the player views need to have something to encapsulate them all
        playerContainer = new JPanel();
        playerContainer.setMinimumSize(new Dimension());
        playerContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel rightSide = new JPanel();
        rightSide.setMinimumSize(new Dimension()); //this includes the BoardView and the Player Views
        rightSide.add(boardView);
        rightSide.add(playerContainer);

        add(rightSide, BorderLayout.EAST);

    }

    protected ConsoleView getConsoleView() {
        return consoleView;
    }

    protected ArrayList<PlayerView> getPlayerViews() {
        return playerViews;
    }

    protected BoardView getBoardView() {
        return boardView;
    }

    protected SharedResourcesView getSharedResourcesView() {
        return sharedResourcesView;
    }

    protected void addPlayerView( PlayerView playerView ) {
        //add the playerview to the player array list
        playerViews.add( playerView );

        //add the player to the view in order to display
        playerContainer.add(playerView);
    }
}
