package view.controls;

import models.palacefestival.FestivalPlayer;
import models.palacefestival.PalaceCard;
import view.MediaController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

//TODO [Sydney][Jorge]

public class FestivalPlayerView extends JPanel {
    private MediaController mediaC;
    private JLabel currentBid;
    private ArrayList<JLabel> palaceCards;
    private JButton dropOut;
    private boolean isDroppedOut;

    public FestivalPlayerView(MediaController media){
        this.mediaC = media;
        currentBid = new JLabel();
        palaceCards = new ArrayList<JLabel>();
        dropOut = new JButton();

        initializeLayout();
    }

    private void initializeLayout(){

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


    public void setCurrentBid(int bid){
        currentBid.setText(""+bid);
    }

    public void setPalaceCards(ArrayList<PalaceCard> cards){

    }

    public void setIsDroppedOut(boolean dropped){
        isDroppedOut = dropped;
    }

    public void update(FestivalPlayer player) {
        setCurrentBid(player.getBid());
        setIsDroppedOut(player.isDroppedOut());
        setPalaceCards(player.getCards());
    }
}
