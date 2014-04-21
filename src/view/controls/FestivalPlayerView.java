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
    private final int SCREEN_WIDTH;
    private final int HEIGHT;

    private Color playerColor;
    private JLabel playerName;
    private JLabel currentBid;
    private JPanel cardContainer;
    private List<JLabel> palaceCards;
    private boolean isDroppedOut;

    public FestivalPlayerView(int cardWidth, int cardHeight, int screenWidth, int height){
        CARD_WIDTH = cardWidth;
        CARD_HEIGHT = cardHeight;
        SCREEN_WIDTH = screenWidth;
        HEIGHT = height;

        playerColor = Color.BLACK;
        playerName = new JLabel();
        currentBid = new JLabel();
        cardContainer = new JPanel();
        palaceCards = new ArrayList<JLabel>();

        initializeLayout();
    }

    private void initializeLayout(){
        setPreferredSize(new Dimension(SCREEN_WIDTH/4, HEIGHT));
        setBackground(Color.WHITE);
        setNotCurrentPlayer();

        playerName.setPreferredSize(new Dimension(getWidth()/2, BORDER*2));
        playerName.setBorder(new EmptyBorder(BORDER/2, 0, 0, BORDER/2));
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

    public void setCurrentPlayer(){
        setBorder(new LineBorder(playerColor, 2));
        unHighlightAllCards();
        displayAllCards();
    }

    public void setNotCurrentPlayer(){
        setBorder(new LineBorder(Color.WHITE, 1));
        unHighlightAllCards();
        hideAllCards();
    }

    public void highlightCard(int index){
        palaceCards.get(index).setBorder(new LineBorder(Color.CYAN, 2));
    }

    private void unhighlightCard(JLabel label){
        label.setBorder(new LineBorder(Color.WHITE, 2));
    }

    public void unHighlightAllCards(){
        for(JLabel label : palaceCards){
            unhighlightCard(label);
        }
    }

    private void displayAllCards(){
        removeCurrentCardsFromCardContainer();
        for(JLabel label : palaceCards){
            addCardToContainer(label);
        }
    }

    private void hideAllCards(){
        removeCurrentCardsFromCardContainer();
        for(int i = 0; i < palaceCards.size(); i++){
            addCardToContainer(new JLabel(new ImageIcon(MediaController.getInstance().getImage("card_back.png"))));
        }
    }

    public void dropOutUser(int index){
        setIsDroppedOut(true);
        cardContainer.setVisible(false);
    }

    public void undoDropOutUser(){
        setIsDroppedOut(false);
        cardContainer.setVisible(true);
    }

    public void setCurrentBid(int bid){
        currentBid.setText(""+bid);
    }

    public void setPalaceCards(List<PalaceCard> cards){
        removeCurrentCardsFromCardContainer();

        //convert the palacecards to view jlabels
        palaceCards = new ArrayList<JLabel>(cards.size());
        for(PalaceCard card : cards){
            addCardToPalaceCards(new JLabel(new ImageIcon(MediaController.getInstance().getImage(card.toString()))));
        }

        unHighlightAllCards();
    }

    private void removeCurrentCardsFromCardContainer(){
        for(JLabel label : palaceCards){
            cardContainer.remove(label);
        }
    }

    private void addCardToPalaceCards(JLabel label){
        palaceCards.add(label);
        addCardToContainer(label);
    }

    private void addCardToContainer(JLabel label){
        cardContainer.add(label);
    }

    private void setIsDroppedOut(boolean dropped){
        isDroppedOut = dropped;
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

    public void update(FestivalPlayer player) {
        setCurrentBid(player.getBid());
        //dropOutUser(player.isDroppedOut());
        setPalaceCards(player.getCards());
        //setSelectedCard(turnController.getSelectedCard());
    }
}
