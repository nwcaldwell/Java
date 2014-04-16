package view.screens.gameplay;

import view.MediaController;
import view.ViewController;
import view.controls.PlayerView;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlayView extends GameplayView {
    private JButton togglePlanningMode;

    public PlayView(ViewController viewC) {
        super(viewC);

        togglePlanningMode = new JButton("Planning Mode"); //TODO
        toggleButtonContainer.add(togglePlanningMode, BorderLayout.NORTH);

        //TODO delete after testing is done/after figure out how to get this incorporated:
        for(int i = 0; i < 4; i++){
            PlayerView player = new PlayerView();
            this.addPlayerView(player);
        }
    }


}
