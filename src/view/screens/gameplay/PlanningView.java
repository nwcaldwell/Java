package view.screens.gameplay;

import view.MediaController;
import view.ViewController;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlanningView extends GameplayView {
    private JButton togglePlayMode;

    public PlanningView(ViewController viewC, MediaController mediaC) {
        super(viewC, mediaC);

        togglePlayMode = new JButton("Play Mode"); //TODO media controller
        toggleButtonContainer.add(togglePlayMode, BorderLayout.NORTH);
    }
}
