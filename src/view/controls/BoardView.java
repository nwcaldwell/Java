package view.controls;

import models.board.Board;
import models.board.HexDirection;
import view.ViewController;

import javax.swing.*;
import java.util.ArrayList;

//TODO [Sydney Christopher] [Jorge]

public abstract class BoardView extends JPanel {

	protected ViewController viewC;
	protected Board board;

    public BoardView(ViewController vc, Board board){
        this.viewC = vc;
        this.board = board;
    }
    
    /**redraws the entire board*/
    public abstract void update();
    
    /**draws a hilighted space*/
    public abstract void hilightSpace(ArrayList<HexDirection> path, int height);
    
    /**draws a developer*/
    public abstract void displayDev(ArrayList<HexDirection> path, int height);
}
