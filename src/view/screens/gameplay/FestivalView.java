package view.screens.gameplay;

import gamecontrollers.Response;
import gamecontrollers.palacefestival.FestivalTurnController;
import models.palacefestival.PalaceCard;
import view.MediaController;
import view.View;
import view.ViewController;
import gamecontrollers.Facade;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import view.commands.JavaKeyListener;
import view.commands.gameplayInput.*;
import view.controls.ConsoleView;
import view.controls.FestivalPlayerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

//TODO [Sydney][Jorge]

public abstract class FestivalView extends View {
    private static final int BORDER = 10;
    private String imageExt = ".png";
    private ConsoleView consoleView;
    private ArrayList<FestivalPlayerView> players;
    private JLabel festivalCard;
    private JLabel highestBid;

    protected abstract void initKeyListeners(ViewController viewC);


    /*
    ========================================================================
      Set up the layout
    ========================================================================
    */

    @Override
    public void init() {
        consoleView = new ConsoleView();
        players = new ArrayList<FestivalPlayerView>(4);
        festivalCard = new JLabel();
        highestBid = new JLabel();

        //setup view
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
    }

    /*
    ========================================================================
      Refreshing view methods
    ========================================================================
    */

    public void setFestivalCard(String imageSource){
        festivalCard.setIcon(new ImageIcon(MediaController.getInstance().getImage(imageSource.concat(imageExt))));
    }

    public void setHighestBid(int bid){
        highestBid.setText("Highest bid: "+bid);
    }

    //TODO test this to make sure it works
    public void update(){
        FestivalModel festivalModel = Facade.getInstance().getFestivalModel();

        setFestivalCard(festivalModel.getFestivalCard().toString());
        setHighestBid(festivalModel.getHighestBid());

        List<FestivalPlayer> fPlayers = festivalModel.getTurnOrder();
        int indexOfCurrentPlayer = festivalModel.getIndexOfCurrentPlayer();
        for(int i = 0; i < fPlayers.size(); i++){
            players.get(i).update(fPlayers.get(i), (i == indexOfCurrentPlayer), festivalModel.getIndexOfCurrentCard());
        }

        getViewController().setFrameAsFocused();
    }

    /*
    ========================================================================
      Needs player input
    ========================================================================
    */

    @Override
    public void displayResponseToConsole(Response response) {
        consoleView.displayMessage(response);
    }

    //TODO does this go here or the view controller?
    public boolean alertUserThatNeedToPlayMoreCards(){
        int response = JOptionPane.showConfirmDialog(this, "You have not bid enough points, would you like to drop out?", "Cannot End Turn", JOptionPane.YES_NO_OPTION);
        if (response == 0) return true;
        return false;
    }

    //TODO does this go here or the view controller?
    public void displayWinners(String winners, int points){

    }

    /*
    ========================================================================
      Getters
    ========================================================================
    */

    public JLabel getHighestBid() {
        return highestBid;
    }

    public JLabel getFestivalCard() {
        return festivalCard;
    }

    public List<FestivalPlayerView> getPlayers() {
        return players;
    }

    public FestivalView(ViewController viewC){
        super(viewC);
        initKeyListeners(viewC);
    }

}
