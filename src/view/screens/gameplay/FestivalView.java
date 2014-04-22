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
    private JButton endFestivalFromTieButton;

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
        endFestivalFromTieButton = new JButton();

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

    public void setTieButton(boolean display){
        endFestivalFromTieButton.setVisible(display);
    }

    //TODO test this to make sure it works
    public void update(){
        FestivalModel festivalModel = Facade.getInstance().getFestivalModel();

        System.out.println(festivalModel.getFestivalCard());
        setFestivalCard(festivalModel.getFestivalCard().toString());
        setHighestBid(festivalModel.getHighestBid());
        setTieButton(festivalModel.checkForTie());

        List<FestivalPlayer> fPlayers = festivalModel.getTurnOrder();
        int indexOfCurrentPlayer = festivalModel.getIndexOfCurrentPlayer();
        System.out.println(fPlayers.size());
        System.out.println(players.size());
        if(fPlayers.size() == players.size())
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

    public JButton getTieButton(){
        return endFestivalFromTieButton;
    }

    public List<FestivalPlayerView> getPlayers() {
        return players;
    }

    public FestivalView(ViewController viewC){
        super(viewC);
        initKeyListeners(viewC);
    }

}
