package view.screens.gameplay;

import view.MediaController;
import view.ViewController;
import view.commands.JavaButtonListener;
import view.commands.NavCommand;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlanningView extends GameplayView {
    private JButton togglePlayMode;

    public PlanningView(ViewController viewC) {
        super(viewC);

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
