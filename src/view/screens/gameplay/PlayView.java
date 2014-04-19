package view.screens.gameplay;

import view.MediaController;
import view.ViewController;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlayView extends GameplayView {
    private JButton togglePlanningMode;

    public PlayView(ViewController viewC, MediaController mediaC) {
        super(viewC);

        togglePlanningMode = new JButton("Planning Mode"); //TODO
        toggleButtonContainer.add(togglePlanningMode, BorderLayout.NORTH);
    }
}
