package view.controls;

import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;
import view.MediaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//TODO [Sydney][Jorge]

public class FestivalPlayerView extends JPanel {
    private static final int BORDER = 10;
    private final int CARD_WIDTH;
    private final int CARD_HEIGHT;
    private final int WIDTH;
    private final int HEIGHT;

    private Color playerColor;
    private JLabel playerName;
    private JLabel currentBid;
    private JPanel cardContainer;
    private List<JLabel> palaceCards;
    private JPanel playedCardsPanel;

    public FestivalPlayerView(int cardWidth, int cardHeight, int width, int height, JPanel cardPanel){
        CARD_WIDTH = cardWidth;
        CARD_HEIGHT = cardHeight;
        WIDTH = width;
        HEIGHT = height;
        playedCardsPanel = cardPanel;

        playerColor = Color.BLACK; //TODO get the player color
        playerName = new JLabel();
        currentBid = new JLabel();
        cardContainer = new JPanel();
        palaceCards = new ArrayList<JLabel>();

        initializeLayout();
    }

    private void initializeLayout(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        setNotCurrentPlayer();

        playerName.setPreferredSize(new Dimension(CARD_WIDTH, BORDER*2));
        playerName.setBorder(new EmptyBorder(BORDER, BORDER/2, 0, BORDER/2));
        playerName.setBackground(Color.WHITE);
        add(playerName);

        currentBid.setPreferredSize(new Dimension(playerName.getWidth(), playerName.getHeight()));
        currentBid.setBorder(new EmptyBorder(BORDER/2, 0, 0, 0));
        currentBid.setBackground(Color.WHITE);
        add(currentBid);

        cardContainer.setPreferredSize(new Dimension(getWidth(), getHeight() - playerName.getHeight()));
        cardContainer.setBackground(Color.WHITE);
        cardContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private void setCurrentPlayer(boolean current, int indexOfCurrentCard){
        if(current) {
            setCurrentPlayer();
            highlightCard(indexOfCurrentCard);
        }
        else
            setNotCurrentPlayer();
    }

    private void setCurrentPlayer(){
        setBorder(new LineBorder(playerColor, 2));
    }

    private void setNotCurrentPlayer(){
        setBorder(new LineBorder(Color.WHITE, 1));
        hideAllCards();
    }

    private void highlightCard(int index){
        palaceCards.get(index).setBorder(new LineBorder(Color.CYAN, 2));
    }

    private void hideAllCards(){
        removeCurrentCardsFromCardContainer();
        Image cardBack = MediaController.getInstance().getImage("card_back.png");
        for(JLabel label : palaceCards){
            label.setIcon(new ImageIcon(cardBack));
        }
    }

    private void addCardsToPlayedCardPanel(List<JLabel> cardsInPlayedCardPanel){
        removeCardsFromPlayedCardPanel();
        for(JLabel card : cardsInPlayedCardPanel){
            playedCardsPanel.add(card);
        }
    }

    private void removeCardsFromPlayedCardPanel(){
        for(Component component : playedCardsPanel.getComponents()){
            playedCardsPanel.remove(component);
        }
    }

    private void setDropedOut(){
        removeCurrentCardsFromCardContainer();
        cardContainer.setBackground(Color.GRAY);
        playerName.setBackground(Color.GRAY);
        currentBid.setBackground(Color.GRAY);
    }

    private void setCurrentBid(int bid){
        currentBid.setText(""+bid);
    }

    private void updateCardContainer(){
        removeCurrentCardsFromCardContainer();
        for(JLabel label : palaceCards)
            cardContainer.add(label);
    }

    private void removeCurrentCardsFromCardContainer(){
        for(JLabel label : palaceCards){
            cardContainer.remove(label);
        }
    }

    private Color convertStringToColor(String colorString){
        Color newColor = Color.black;
        try {
            Field field = Color.class.getField(colorString);
            newColor = (Color)field.get(null);
        } catch (Exception e) {
            System.out.println("There was an image parsing the color string to a color");
        }

        return newColor;
    }

    private List<JLabel> convertPalaceCards(List<PalaceCard> cards){
        //convert the palacecards to view jlabels
        List<JLabel> cardsLabels = new ArrayList<JLabel>(cards.size());
        for(PalaceCard card : cards){
            add(new JLabel(new ImageIcon(MediaController.getInstance().getImage(card.toString()))));
        }
        return cardsLabels;
    }

    public void update(FestivalPlayer player, boolean isCurrentPlayer, int indexOfCurrentCard) {
        if(!player.isDroppedOut()) {
            //set the players current bid
            setCurrentBid(player.getBid());

            //set and update the players unpaid
            palaceCards = convertPalaceCards(player.getCards());
            removeCurrentCardsFromCardContainer();
            updateCardContainer();

            //set if the player is the current player
            setCurrentPlayer(isCurrentPlayer, indexOfCurrentCard);

            //update the cards that are played
            addCardsToPlayedCardPanel(convertPalaceCards(player.getDiscardedCards()));
        }
        else
            setDropedOut();
    }
}
