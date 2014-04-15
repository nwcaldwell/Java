package view.cgi;

import java.util.ArrayList;

import models.board.Board;
import models.board.Direction;
import models.board.HexSpace;
import models.board.HexDirection;
import models.board.HexTileComponent;
import view.MediaController;
import view.ViewController;
import view.controls.BoardView;

/**an implementation of BoardView that uses an LWJGL canvas*/
public class LWJGLBoardView extends BoardView{

	public LWJGLBoardView(ViewController vc, MediaController media, Board<HexSpace,HexTileComponent,HexDirection> b) {
		super(vc, media,b);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void hilightSpace(ArrayList<HexDirection> dir, int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayDev(ArrayList<HexDirection> dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}