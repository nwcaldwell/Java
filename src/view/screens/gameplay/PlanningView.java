package view.screens.gameplay;

import view.ViewController;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlanningView extends GameplayView {
    private JButton togglePlayMode;

    public PlanningView(ViewController viewC) {
        super(viewC);
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {
        togglePlayMode = new JButton("Play Mode");
        togglePlayMode.setFocusable(false);
        disablePlayModeButton();
//        togglePlayMode.addActionListener(new JavaButtonListener(new NavCommand(this.getViewController(), new PlayView(this.getViewController()))));
        toggleButtonContainer.add(togglePlayMode, BorderLayout.NORTH);
    }

    public void enablePlayModeButton(){
        togglePlayMode.setEnabled(true);
    }

    public void disablePlayModeButton(){
        togglePlayMode.setEnabled(false);
    }
}
