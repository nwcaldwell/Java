package view.cgi;

import models.board.Board;
import models.board.HexDirection;
import models.board.Direction;
import models.board.Space;
import view.ViewController;
import view.controls.BoardView;

import java.awt.Canvas;
import java.io.File;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

/**an implementation of BoardView that uses an LWJGL canvas*/
public class LWJGLBoardView extends BoardView{
	
	public static final float CANVAS_WIDTH=10;
	public static final float CANVAS_HEIGHT=10;
	final float CLOSE=0.01f;
	final float FAR=1;
	
	private Canvas lwjglCanvas;
	
	protected static final Vector2D[] offsets = new Vector2D[HexDirection.values().length];
	
	private static final float HEX_DISTANCE=(float) Math.sin(60*2*Math.PI/360)*2;
	
	private static final float HEX_ANGLE=(float)Math.PI/3f;
	
	//initialize offsets
	static{
		Vector2D north = new Vector2D(HEX_DISTANCE,0);
		offsets[HexDirection.N.getIntValue()]=north;
		offsets[HexDirection.NE.getIntValue()]=north.rotate(HEX_ANGLE);
		offsets[HexDirection.SE.getIntValue()]=north.rotate(HEX_ANGLE*2);
		offsets[HexDirection.S.getIntValue()]=north.rotate(HEX_ANGLE*3);
		offsets[HexDirection.SW.getIntValue()]=north.rotate(HEX_ANGLE*4);
		offsets[HexDirection.NW.getIntValue()]=north.rotate(HEX_ANGLE*5);
	};
	
	
	/**the height, in local units, of a hex tile*/
	final float spaceHeight=0.2f;

	/**represents a model of a developer.*/
	Model3D developer=null;
	
	/**represents a model that represents a hilighted space*/
	Model3D hilight=null;
	/**represents the model of a space whose surface is not seen*/
	Model3D buriedSpace=null;
	/**represents the model of a space whose surface is not seen*/
	Model3D rice=null;
	/**represents the model of a space whose surface is not seen*/
	Model3D village=null;
	/**represents the model of a space whose surface is not seen*/
	Model3D irrigation=null;
	
	ArrayList<Model3D> spaces=new ArrayList<Model3D>();
	ArrayList<Model3D> developers=new ArrayList<Model3D>();
	ArrayList<Model3D> hilights=new ArrayList<Model3D>();
	
	/**for perspective, the translation of the scene*/
	private Vector3D sceneTranslation;
	/**for perspective, the yaw of the scene*/
	private double sceneYaw=0;
	/**for perspective, the pitch of the scene*/
	private double scenePitch=0;
	
	public LWJGLBoardView(ViewController vc, Board b) {
		super(vc, b);
	}
	
	/**initializes OpenGL Must be called after this object is added to something
	 * on a displayable JFrame.
	 * @throws Exception */
	public void initGL() throws Exception{
		if (!getParent().isDisplayable()){
			throw new Exception("LWJGLBoardView must be attached to a displayable object\n" +
					"specifically, it must return true for the \"isDisplayable\" call");
		}
		
		//create a canvas to hold the LWJGL Display
		lwjglCanvas = new Canvas();
		add(lwjglCanvas);
		lwjglCanvas.setSize(lwjglCanvas.getParent().getSize());
		
		//attach the LWJGL Display to its canvas
		//for the record, I think the width and height don't matter.
		Display.setDisplayMode(new DisplayMode(100,100));//(int)CANVAS_WIDTH, (int)CANVAS_HEIGHT));
		Display.setParent(lwjglCanvas);
		Display.create();
		//enable all of the OpenGL stuff
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		//we need textures
		glEnable(GL_TEXTURE_2D);
		//depth test ensures that close stuff is in front of far stuff.
		glEnable(GL_DEPTH_TEST);
		//enable lighting (specifically light 0)
		//glEnable(GL_LIGHTING);
		//glEnable(GL_LIGHT0);
		//ensures normalization of all norms
		//a bit slow.  Can be removed and fixed in face for optimization.
		glEnable(GL_NORMALIZE);
		//enable face culling, so only the front of a shape is rendered
		//EASY way to optimize
		glCullFace(GL_FRONT);
		glEnable(GL_CULL_FACE);

		glClearColor(0.5f, 0, 0, 1);
		
		//try loading the default texture.  If this doesn't work, you're screwed.
		TextureFactory.loadMissingTexture("resources/imgs/Default.png");
		
		loadResources();
	}
	
	private void loadResources(){
		buriedSpace=ModelFactory.makeFromObj(new File("resources/hex.obj"), 
				TextureFactory.getTexture("resources/imgs/GenericHex.png"));
	}
	
	@Override
	public void hilightSpace(ArrayList<Direction> path, int height) {
		Vector2D offset=new Vector2D(0,0);
		for (Direction d:path){
			offset.translate(offsets[d.getIntValue()]);
		}
		Model3D newModel=hilight.clone();
		newModel.setTranslation(new Vector3D(offset.x, height*spaceHeight, offset.y));
	}
	
	@Override
	public void displayDev(ArrayList<Direction> path, int height) {
		Vector2D offset=new Vector2D(0,0);
		for (Direction d:path){
			offset.translate(offsets[d.getIntValue()]);
		}
		Model3D newModel=developer.clone();
		newModel.setTranslation(new Vector3D(offset.x, height*spaceHeight, offset.y));
	}

	@Override
	public void update() {
		spaces.clear();
		ArrayList<Space> preTraversed=new ArrayList<Space>();
		updateRecursive(board.getRoot(), preTraversed, new Vector2D(0, 0));
	}
	
	/**updates the model data for the spaces on the
	 * board recursively.*/
	private void updateRecursive(Space root, ArrayList<Space> preTraversed, Vector2D offset){
		updateSpace(root, offset);
		preTraversed.add(root);
		for (int i=0;i<HexDirection.values().length;i++){
			Space adjacent=root.getAdjacentSpace(HexDirection.values()[i]);
			if (adjacent!=null&&!preTraversed.contains(adjacent)){
				updateRecursive(adjacent, preTraversed, offset.translate(offsets[i]));
			}
		}
	}
	
	/**adds model data for a given space.
	 * May be more than one Model3D.*/
	private void updateSpace(Space space, Vector2D offset){
		for (int i=0;i<space.getHeight()-1;i++){
			Model3D newModel=buriedSpace.clone();
			newModel.setTranslation(new Vector3D(offset.x, i*spaceHeight, offset.y));
			spaces.add(newModel);
		}
		if (space.getHeight()>0||true){
			//render the top tile
			Model3D newModel=buriedSpace.clone();
			newModel.setTranslation(new Vector3D(offset.x, space.getHeight()*spaceHeight, offset.y));
			spaces.add(newModel);
		}
	}
	
	/**maybe, if we were doing a complicated model, I would feel the need
	 * to add a scene-graph system.  As it is, nothing we use would take
	 * advantage of it, so no scene graph has been made.*/
	public void renderScene(){
		
		//modelview doesn't translate lights
		glMatrixMode(GL11.GL_MODELVIEW);
		//reset the transformation matrix
		glLoadIdentity();
		//Creates an orthographic projection from 0,0 to CANVAS_WIDTH, CANVAS_HEIGHT
		glOrtho(0, CANVAS_WIDTH, 0, CANVAS_HEIGHT, 1, 100);
		//setLighting();
		//clear the background
		glClear(GL11.GL_COLOR_BUFFER_BIT);
		//if we want a skybox, we should put it here
		//SKYBOX!!!
		//clear depth buffers
		glClear(GL11.GL_DEPTH_BUFFER_BIT);
		
		//things too close will not be rendered
		glTranslated(0, 0, -50);
		
		glTranslated(sceneTranslation.x, sceneTranslation.y, sceneTranslation.z);
		
		glRotated(scenePitch, 1, 0, 0);
		
		glRotated(sceneYaw, 0, 1, 0);
		
		for (Model3D model: spaces){
			model.render();
		}
		for (Model3D model: developers){
			model.render();
		}
		for (Model3D model: hilights){
			model.render();
		}
		
		Display.update();
	}

	////////////////getters and setters////////////////
	
	public Vector3D getSceneTranslation() {
		return sceneTranslation;
	}

	public void setSceneTranslation(Vector3D sceneTranslation) {
		this.sceneTranslation = sceneTranslation;
	}

	public double getSceneYaw() {
		return sceneYaw;
	}

	public void setSceneYaw(double sceneYaw) {
		this.sceneYaw = sceneYaw;
	}

	public double getScenePitch() {
		return scenePitch;
	}

	public void setScenePitch(double scenePitch) {
		this.scenePitch = scenePitch;
	}
}