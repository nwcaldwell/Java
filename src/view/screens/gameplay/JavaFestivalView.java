package view.screens.gameplay;

import view.ViewController;
import view.commands.JavaKeyListener;
import view.commands.gameplayInput.*;
import view.controls.FestivalPlayerView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyEvent;

/**
 * Created by ssyyddnneeyy on 4/21/14.
 */
public class JavaFestivalView extends FestivalView {
    private static final int BORDER = 10;
    private static final int CARD_WIDTH = 48;
    private static final int CARD_HEIGHT = 70;
    private JPanel[] playedCardPanels;

    public JavaFestivalView(ViewController viewC){
        super(viewC);
    }

    public void init(){
        super.init();

        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(this.getScreenHeight()-BORDER*2, this.getScreenHeight()-BORDER*2));
        center.setBackground(new Color(5, 125, 43));
        center.setLayout(new BorderLayout());

        getFestivalCard().setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        getHighestBid().setPreferredSize(new Dimension(CARD_WIDTH, BORDER * 2));
        center.add(getHighestBid(), BorderLayout.CENTER);
        center.add(getFestivalCard(), BorderLayout.CENTER);

        //placeholders for the cards that the user plays
        playedCardPanels = new JPanel[4];

        add(center, BorderLayout.CENTER);
        //there are at most 4 players, need to set them up in a special way
        //need the white space if they don't have a player associated for prettiness
        List<FestivalPlayerView> players = getPlayers();
        for(int i = 0; i < playedCardPanels.length; i++) {
            playedCardPanels[i] = new JPanel();
            playedCardPanels[i].setBackground(new Color(5, 125, 43));
            if(i % 2 == 1) {
                playedCardPanels[i].setPreferredSize(new Dimension(center.getWidth() - BORDER, (center.getHeight() - CARD_HEIGHT)/2));
                players.add(new FestivalPlayerView(CARD_WIDTH, CARD_HEIGHT, this.getScreenWidth() - BORDER * 2, CARD_HEIGHT + BORDER * 2, playedCardPanels[i]));
            }
            else {
                playedCardPanels[i].setPreferredSize(new Dimension((center.getWidth() - CARD_WIDTH)/2, center.getHeight() - BORDER));
                players.add(new FestivalPlayerView(CARD_WIDTH, CARD_HEIGHT, CARD_HEIGHT + BORDER * 2, this.getScreenHeight() - 2 * (CARD_HEIGHT + BORDER * 2), playedCardPanels[i]));
            }
        }

        add(players.get(0), BorderLayout.SOUTH);
        add(playedCardPanels[0], BorderLayout.SOUTH);

        add(players.get(1), BorderLayout.WEST);
        add(playedCardPanels[0], BorderLayout.WEST);

        add(players.get(2), BorderLayout.NORTH);
        add(playedCardPanels[0], BorderLayout.NORTH);

        add(players.get(3), BorderLayout.EAST);
        add(playedCardPanels[0], BorderLayout.EAST);
    }

        /*
    ========================================================================
      Key Listeners
      //TODO are these changing? Kevin?
    ========================================================================
    */

    public void initKeyListeners(ViewController viewController){
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ENTER, new AcceptTieRequestCommand(viewController))); //TODO how we do this?
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ESCAPE, new CancelCurrentActionInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_D, new DropOutOfFestivalInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_X, new EndFestivalTurnInputCommand(viewController)));

        keyListeners.add(new JavaKeyListener(KeyEvent.VK_ENTER, new PlayPalaceCardInputCommand(viewController)));
        keyListeners.add(new JavaKeyListener(KeyEvent.VK_TAB, new TabPalaceCardInputCommand(viewController)));
    }

}
