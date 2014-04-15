package view.controls;

import java.util.ArrayList;

import view.MediaController;
import view.ViewController;

import javax.swing.*;

import models.board.Board;
import models.board.HexDirection;
import models.board.Space;
import models.board.HexTileComponent;

//TODO [Sydney Christopher] [Jorge]

public abstract class BoardView extends JPanel {
    
	protected MediaController mediaC;
	protected ViewController viewC;
	protected Board board;
    
    public BoardView(ViewController vc, MediaController media, Board board){
        this.mediaC = media;
        this.viewC = vc;
        this.board = board;
    }
    
    /**redraws the entire board*/
    public abstract void update();
    
    /**draws a hilighted space*/
    public abstract void hilightSpace(ArrayList<HexDirection> dir, int height);
    
    /**draws a developer*/
    public abstract void displayDev(ArrayList<HexDirection> dir);
}
