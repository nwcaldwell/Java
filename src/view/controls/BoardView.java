package view.controls;

import models.board.Board;
import models.board.HexDirection;
import models.board.HexSpace;
import models.board.HexTileComponent;
import view.ViewController;

import javax.swing.*;
import java.util.ArrayList;

//TODO [Sydney Christopher] [Jorge]

public abstract class BoardView extends JPanel {

	protected ViewController viewC;
	protected Board<HexSpace, HexTileComponent, HexDirection> board;
    
    public BoardView(ViewController vc, Board<HexSpace, HexTileComponent, HexDirection> board){
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
