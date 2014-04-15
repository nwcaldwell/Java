package view.cgi;

import java.util.ArrayList;

import models.board.Board;
import models.board.Developer;
import models.board.Direction;
import models.board.HexSpace;
import models.board.HexDirection;
import models.board.HexTileComponent;
import models.board.Space;
import view.MediaController;
import view.ViewController;
import view.controls.BoardView;

/**an implementation of BoardView that uses an LWJGL canvas*/
public class LWJGLBoardView extends BoardView{
	
	final Vector2D[] offsets = new Vector2D[HexDirection.values().length];
	
	/*cannot be instantiated until HexDirection is implemented more;
	{
		new Vector2D(),
		new Vector2D(),
		new Vector2D(),
		new Vector2D(),
		new Vector2D(),
		new Vector2D()	
	};
	*/
	
	/**the height, in local units, of a hex tile*/
	final float spaceHeight=1;

	/**represents a model of a developer.*/
	final Model3D developer=null;
	
	/**represents a model that represents a hilighted space*/
	final Model3D hilight=null;
	/**represents the model of a space whose surface is not seen*/
	final Model3D buriedSpace=null;
	/**represents the model of a space whose surface is not seen*/
	final Model3D rice=null;
	/**represents the model of a space whose surface is not seen*/
	final Model3D village=null;
	/**represents the model of a space whose surface is not seen*/
	final Model3D irrigation=null;
	
	ArrayList<Model3D> spaces=new ArrayList<Model3D>();
	ArrayList<Model3D> developers=new ArrayList<Model3D>();
	ArrayList<Model3D> hilights=new ArrayList<Model3D>();
	
	public LWJGLBoardView(ViewController vc, MediaController media, Board<HexSpace,HexTileComponent,HexDirection> b) {
		super(vc, media,b);
		// TODO Auto-generated constructor stub
	}
	
	/**initializes OpenGL*/
	public void initGL(){
		
	}
	
	@Override
	public void hilightSpace(ArrayList<HexDirection> path, int height) {
		Vector2D offset=new Vector2D(0,0);
		for (HexDirection d:path){
			offset.translate(offsets[d.ordinal()]);
		}
		Model3D newModel=hilight.clone();
		newModel.setTranslation(new Vector3D(offset.x, height*spaceHeight, offset.y));
	}
	
	@Override
	public void displayDev(ArrayList<HexDirection> path, int height) {
		Vector2D offset=new Vector2D(0,0);
		for (HexDirection d:path){
			offset.translate(offsets[d.ordinal()]);
		}
		Model3D newModel=developer.clone();
		newModel.setTranslation(new Vector3D(offset.x, height*spaceHeight, offset.y));
	}

	@Override
	public void update() {
		spaces.clear();
		ArrayList<HexSpace> preTraversed=new ArrayList<HexSpace>();
		board.getRoot();
		
	}
	
	/**updates the model data for the spaces on the
	 * board recursively.*/
	private void updateRecursive(HexSpace root, ArrayList<HexSpace> preTraversed, Vector2D offset){
		updateSpace(root, offset);
		preTraversed.add(root);
		for (int i=0;i<HexDirection.values().length;i++){
			HexSpace adjacent=root.getAdjacentSpace(HexDirection.values()[i]);
			if (adjacent!=null&&!preTraversed.contains(adjacent)){
				updateRecursive(adjacent, preTraversed, offset.translate(offsets[i]));
			}
		}
	}
	
	/**adds model data for a given space.
	 * May be more than one Model3D.*/
	private void updateSpace(HexSpace space, Vector2D offset){
		for (int i=0;i<space.getHeight()-1;i++){
			Model3D newModel=buriedSpace.clone();
			newModel.setTranslation(new Vector3D(offset.x, i*spaceHeight, offset.y));
			spaces.add(newModel);
		}
	}
}