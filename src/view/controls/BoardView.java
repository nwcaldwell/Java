package view.controls;

import models.board.Board;
import models.board.Direction;
import models.board.TileComponent;
import view.ViewController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//TODO [Sydney Christopher] [Jorge]

public abstract class BoardView extends JPanel {
    private final int BORDER = 10;
    private final int WIDTH = (int)(3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - BORDER/2);
    private final int HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 300 - BORDER); //see PlayerView for Height
	protected Board board;
    private ViewController controller;

    public BoardView(Board board, ViewController vc){
        this.board = board;
        this.controller = vc;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
    }
    
    /**redraws the entire board*/
    public abstract void update();
    
    /**draws a hilighted space*/
    public abstract void hilightSpace(ArrayList<Direction> path, int height);
    
    /**draws a developer*/
    public abstract void displayDev(ArrayList<Direction> path, int height);

    public abstract void addTiles(TileComponent root, ArrayList<Direction> path, int height);
    
    public void setFrameAsFocused(){
        controller.setFrameAsFocused();
    }
}
