package view.screens.gameplay;

import view.*;
import view.controls.FestivalPlayerView;

import javax.swing.*;
import java.util.List;

//TODO [Sydney][Jorge]

public class FestivalView extends View {

    private List<FestivalPlayerView> playersList;
    private JLabel festivalCard;
    private JLabel highestBid;

    public FestivalView(ViewController viewC, MediaController mediaC){
        super(viewC);

    }
}
