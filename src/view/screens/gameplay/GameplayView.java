package view.screens.gameplay;

import view.View;
import view.ViewController;
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
    //protected BoardView boardView;
    protected SharedResourcesView sharedResourcesView;
    protected JPanel playerContainer;
    protected JPanel toggleButtonContainer;

    protected GameplayView(ViewController viewC) {
        super(viewC);
    }

    //this method sets up the default layout for the board
    public void init(){
        //create the attributes
        consoleView = new ConsoleView();
        playerViews = new ArrayList<PlayerView>();
        //boardView = new LWJGLBoardView(viewC, Facade.getInstance().getBoard()); //TODO get this working properly
        sharedResourcesView = new SharedResourcesView();

        //setup view
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        //this is the left side of the screen, needs to somehow conform to the size of the screen
        JPanel leftSide = new JPanel();
        leftSide.setPreferredSize(new Dimension(this.getScreenWidth() / 4 - BORDER / 2, this.getScreenHeight() - BORDER * 2));
        leftSide.setBackground(Color.WHITE);

        //the shared resources and the console are on the left panel. add them in the respective order
        toggleButtonContainer = new JPanel();
        leftSide.add(sharedResourcesView);
        sharedResourcesView.add(toggleButtonContainer);
        leftSide.add(consoleView);
        add(leftSide, BorderLayout.WEST);

        //the player views need to have something to encapsulate them all
        playerContainer = new JPanel();
        playerContainer.setMinimumSize(new Dimension(3*this.getScreenWidth()/4 - BORDER/2, this.getScreenHeight()/4- BORDER));

        //this includes the BoardView and the Player Views
        JPanel rightSide = new JPanel();
        rightSide.setPreferredSize(new Dimension(3 * this.getScreenWidth() / 4 - BORDER / 2, this.getScreenHeight() - BORDER * 2));
        rightSide.setBackground(Color.WHITE);
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

//    protected BoardView getBoardView() {
//        return boardView;
//    }

    protected SharedResourcesView getSharedResourcesView() {
        return sharedResourcesView;
    }

    protected void addPlayerView(PlayerView player) {
        //add the playerview to the player array list
        playerViews.add( player );

        //add the player to the view in order to display
        playerContainer.add(player);
    }

    public void update(){
        //boardView.update(); TODO
        sharedResourcesView.update();
        consoleView.update();


    }

}
