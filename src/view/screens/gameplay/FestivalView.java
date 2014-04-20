package view.screens.gameplay;

import gamecontrollers.Facade;
import models.palacefestival.FestivalModel;
import models.palacefestival.FestivalPlayer;
import view.*;
import view.cgi.Face3D;
import view.controls.ConsoleView;
import view.controls.FestivalPlayerView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//TODO [Sydney][Jorge]

public class FestivalView extends View {
    private static final int BORDER = 10;
    private String imageExt = ".png";
    private ConsoleView consoleView;
    private ArrayList<FestivalPlayerView> players;
    private JLabel festivalCard;
    private JLabel highestBid;

    public FestivalView(ViewController viewC){
        super(viewC);

        consoleView = new ConsoleView();
        players = new ArrayList<FestivalPlayerView>(4);
        festivalCard = new JLabel();
        highestBid = new JLabel();

        //setup view
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        //initialize everything else
        initializeView();
    }

    //this method sets up the default layout for the board
    protected void initializeView(){
        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(this.getScreenHeight()/2, this.getScreenHeight()/2));
        center.setBackground(new Color(5, 125, 43));
        center.setLayout(new BorderLayout());

        //festivalCard.setIcon(new ImageIcon(MediaController.getInstance().getImage()));
        //festivalCard.setPreferredSize(new Dimension());

        add(center);
    }

    public void setFestivalCard(String imageSource){
        festivalCard.setIcon(new ImageIcon(MediaController.getInstance().getImage(imageSource.concat(imageExt))));
    }

    public void setHighestBid(int bid){
        highestBid.setText(""+bid);
    }

    public void update(){
        FestivalModel festival = Facade.getInstance().getFestival();
        setFestivalCard(festival.getFestivalCard().toString());
        setHighestBid(festival.getHighestBid());

        List<FestivalPlayer> fPlayers = festival.getPlayers();
        for(int i = 0; i < fPlayers.size(); i++){
            players.get(i).update(fPlayers.get(i));
        }
    }
}
