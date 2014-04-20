package view.screens.gameplay;

import view.MediaController;
import view.View;
import view.ViewController;
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

    public void update(){
//        consoleView.update();

    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {
        throw new UnsupportedOperationException();
    }
}
