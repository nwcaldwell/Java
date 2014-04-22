package view.controls;

import models.board.Board;
import models.board.Direction;
import models.board.TileComponent;
import view.ViewController;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

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
    public abstract void hilightSpace(List<Direction> path);
    
    /**draws a developer*/
    public abstract void displayDev(List<Direction> path, Color c);

    public abstract void addTiles(TileComponent root, List<Direction> path);
    
    public void setFrameAsFocused(){
        controller.setFrameAsFocused();
    }
}
