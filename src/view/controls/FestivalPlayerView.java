package view.controls;

import models.palacefestival.FestivalPlayer;
import view.MediaController;

import javax.swing.*;

//TODO [Sydney][Jorge]

public class FestivalPlayerView extends JPanel {
    private MediaController mediaC;
    private JLabel currentBid;
    private JLabel[] palaceCards;
    private JButton dropOut;

    public FestivalPlayerView(MediaController media){
        this.mediaC = media;
    }

    public void update(FestivalPlayer player) {
    }
}
