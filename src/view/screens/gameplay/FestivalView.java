package view.screens.gameplay;

import gamecontrollers.palacefestival.FestivalTurnController;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

//TODO [Sydney][Jorge]

public class FestivalView extends View {
    private static final int BORDER = 10;
    private static final int CARD_WIDTH = 48;
    private static final int CARD_HEIGHT = 70;
    private String imageExt = ".png";
    private ConsoleView consoleView;
    private ArrayList<FestivalPlayerView> players;
    private JLabel festivalCard;
    private JLabel highestBid;

    public FestivalView(ViewController viewC){
        super(viewC);
        initKeyListeners(viewC);
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {
        consoleView = new ConsoleView();    //TODO are we going to have this?
        players = new ArrayList<FestivalPlayerView>(4);
        festivalCard = new JLabel();
        highestBid = new JLabel();

        //setup view
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(this.getScreenHeight()-BORDER*2, CARD_HEIGHT + BORDER*2));
        center.setBackground(new Color(5, 125, 43));
        center.setLayout(new BorderLayout());

        festivalCard.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        highestBid.setPreferredSize(new Dimension(CARD_WIDTH, BORDER*2));
        center.add(highestBid, BorderLayout.CENTER);
        center.add(festivalCard, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        for(int i = 0; i < 4; i++) {
            if(i % 2 == 1)
                players.add(new FestivalPlayerView(CARD_WIDTH, CARD_HEIGHT, this.getScreenWidth(), center.getHeight()));
            else
                players.add(new FestivalPlayerView(CARD_WIDTH, CARD_HEIGHT, center.getHeight(), this.getScreenWidth()));
        }

        add(players.get(0), BorderLayout.SOUTH);
        add(players.get(1), BorderLayout.WEST);
        add(players.get(2), BorderLayout.NORTH);
        add(players.get(3), BorderLayout.EAST);
    }

    private void initKeyListeners(ViewController viewController){
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ENTER, new AcceptTieRequestCommand(viewController))); //TODO how we do this?
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ESCAPE, new CancelCurrentActionInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_D, new DropOutOfFestivalInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_X, new EndFestivalTurnInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ENTER, new PlayPalaceCardInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_TAB, new TabPalaceCardInputCommand(viewController)));
    }

    public void dropOutPlayer(int index){
        players.get(index).dropOutUser(index);
        players.remove(index);
    }

    public void undoDropOutPlayer(int index){
        throw new UnsupportedOperationException();
    }

    public void setFestivalCard(String imageSource){
        festivalCard.setIcon(new ImageIcon(MediaController.getInstance().getImage(imageSource.concat(imageExt))));
    }

    public void setHighestBid(int bid){
        highestBid.setText("Highest bid: "+bid);
    }

    public void update(){
        FestivalModel festival = Facade.getInstance().getFestival();
//        FestivalTurnController currentPlayerInfo = Facade.getInstance().getFestivalController();
        setFestivalCard(festival.getFestivalCard().toString());
        setHighestBid(festival.getHighestBid());

        List<FestivalPlayer> fPlayers = festival.getPlayers();
        for(int i = 0; i < fPlayers.size(); i++){
            players.get(i).update(fPlayers.get(i));
        }
    }
}
