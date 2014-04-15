package view.controls;

import java.util.ArrayList;

import view.MediaController;
import view.ViewController;

import javax.swing.*;

import models.board.HexDirection;

//TODO [Sydney Christopher] [Jorge]

public abstract class BoardView extends JPanel {
    private MediaController mediaC;

    public BoardView(ViewController vc, MediaController media){
        this.mediaC = media;

    }
    
    /**redraws the entire board*/
    public abstract void update();
    
    /**draws a hilighted space*/
    public abstract void hilightSpace(ArrayList<HexDirection> dir, int height);
    
    /**draws a developer*/
    public abstract void displayDev(ArrayList<HexDirection> dir);
}
