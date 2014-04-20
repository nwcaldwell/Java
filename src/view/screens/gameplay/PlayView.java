package view.screens.gameplay;

import view.ViewController;
import view.commands.JavaButtonListener;
import view.commands.NavCommand;
import view.controls.PlayerView;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlayView extends GameplayView {
    private JButton togglePlanningMode;

    /*
    ========================================================================
      Constructors
    ========================================================================
    */
    public PlayView(ViewController viewC) {
        super(viewC);
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {
        togglePlanningMode = new JButton("Planning Mode"); //TODO
        togglePlanningMode.setFocusable(false);
//        disablePlanningModeButton();
        togglePlanningMode.addActionListener(new JavaButtonListener(new NavCommand(this.getViewController(), new PlanningView(this.getViewController()))));
        toggleButtonContainer.add(togglePlanningMode, BorderLayout.NORTH);
    }


    public void enablePlanningModeButton(){
        togglePlanningMode.setEnabled(true);
    }

    public void disablePlanningModeButton(){
        togglePlanningMode.setEnabled(false);
    }


}
