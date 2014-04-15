package view.screens.gameplay;

import gamecontrollers.Facade;
import view.controls.ConsoleView;
import view.MediaController;
import view.View;
import view.ViewController;
import view.cgi.LWJGLBoardView;
import view.controls.BoardView;
import view.controls.PlayerView;
import view.controls.SharedResourcesView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

//TODO [Sydney][Jorge]

public abstract class GameplayView extends View {
    private ConsoleView consoleView;
    private ArrayList<PlayerView> playerViews;
    private BoardView boardView;
    private SharedResourcesView sharedResourcesView;
    JPanel playerContainer;
    JPanel toggleButtonContainer;

    protected GameplayView(ViewController viewC) {
        super(viewC);

        //create the attributes
        consoleView = new ConsoleView();
        playerViews = new ArrayList<PlayerView>();
        BoardView boardView = new LWJGLBoardView(viewC, Facade.getInstance().getBoard());
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
        toggleButtonContainer = new JPanel();
        leftSide.add(toggleButtonContainer);
        leftSide.add(sharedResourcesView);
        leftSide.add(consoleView);
        add(leftSide, BorderLayout.WEST);

        //the player views need to have something to encapsulate them all
        playerContainer = new JPanel();
        playerContainer.setMinimumSize(new Dimension());
        playerContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel rightSide = new JPanel();
        rightSide.setMinimumSize(new Dimension()); //this includes the BoardView and the Player Views
        //rightSide.add(boardView);
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
