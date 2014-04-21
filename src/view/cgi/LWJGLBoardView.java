package view.cgi;

import models.board.Board;
import models.board.HexDirection;
import models.board.Direction;
import models.board.Space;
import models.board.SpaceGeography;
import view.MediaController;
import view.controls.BoardView;

import java.awt.Canvas;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Timer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

/**an implementation of BoardView that uses an LWJGL canvas*/
public class LWJGLBoardView extends BoardView{
	
	public static final float CANVAS_WIDTH=10;
	public static final float CANVAS_HEIGHT=10;
	public static final float CANVAS_FAR=101;
	public static final float CANVAS_NEAR=1;
	final float CLOSE=0.01f;
	final float FAR=1;
	
	private Canvas lwjglCanvas;
	
	protected static final Vector2D[] offsets = new Vector2D[HexDirection.values().length];
	
	/**marks the distance between two hex tiles in rendering (disregards scene scale)*/
	private static final float HEX_DISTANCE=(float) Math.sin(60*2*Math.PI/360)*2*3.461961f;
	
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
	public static final float SPACE_HEIGHT=0.882757f;

	/**represents a model of a developer.*/
	Model3D developer=null;
	
	/**represents a model of the ground beneath a space on central Java*/
	Model3D ground=null;
	/**represents a model of highland terrain*/
	Model3D highland=null;
	/**represents a model of lowland terrain*/
	Model3D lowland=null;
	
	/**represents palaces of various size.
	 * each size is doubled.*/
	Model3D palace[]=new Model3D[5];
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
	private Vector3D sceneTranslation=new Vector3D(0, 0, 0);
	/**for perspective, the yaw of the scene*/
	private double sceneYaw=0;
	/**for perspective, the pitch of the scene*/
	private double scenePitch=0;
	/**for perspective, the scale of the scene*/
	private double sceneScale=0.25f;
	
	public LWJGLBoardView(Board b) {
		super(b);
	}
	
	@Override
	public synchronized void addNotify() {
		super.addNotify();
		try {
			System.out.println("Initializing OpenGL");
			initGL();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**initializes OpenGL Must be called after this object is added to something
	 * on a displayable JFrame.
	 * @throws Exception */
	public synchronized void initGL() throws Exception{
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
		Display.setDisplayMode(new DisplayMode((int)CANVAS_WIDTH, (int)CANVAS_HEIGHT));
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
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		//ensures normalization of all norms
		//a bit slow.  Can be removed and fixed in face for optimization.
		glEnable(GL_NORMALIZE);
		//enable face culling, so only the front of a shape is rendered
		//EASY way to optimize
		glCullFace(GL_FRONT);
		glEnable(GL_CULL_FACE);

		glClearColor(0.5f, 0, 0, 1);
		
		//try loading the default texture.  If this doesn't work, you're screwed.
		TextureFactory.loadMissingTexture("Default.png");
		
		loadResources();
		lwjglCanvas.setBackground(Color.green);
		LWJGLBoardViewInputPoller listener = new LWJGLBoardViewInputPoller(this);
		lwjglCanvas.addMouseListener(listener);
		lwjglCanvas.addMouseMotionListener(listener);
	}
	
	private void loadResources(){
//		buriedSpace=ModelFactory.makeFromObj(MediaController.getInstance().getFile("hex.obj"), 
//				TextureFactory.getTexture("GenericHex.png"));
		Model3D riceHex=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/rice_hex.obj"), 
				TextureFactory.getTexture("3Dobjects/rice_hex_texture.png"));
		this.rice=new Model3D(riceHex);
		this.rice.setRotation(0, 30, 0);
		this.buriedSpace=new Model3D(riceHex);
		this.buriedSpace.setRotation(0, 30, 0);
		this.buriedSpace.setFlat();
		this.ground=new Model3D(riceHex);
		this.ground.setRotation(0, 30, 0);
		this.ground.setFlat();
		Model3D villageHex=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/village_hex.obj"), 
				TextureFactory.getTexture("3Dobjects/village_hex_texture.png"));
		Model3D village=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/village.obj"), 
				TextureFactory.getTexture("3Dobjects/village_texture.png"));
		this.village=new Model3D(village,villageHex);
		this.village.setRotation(0, 30, 0);
		Model3D irrigationHex=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/irrigation_hex.obj"), 
				TextureFactory.getTexture("3Dobjects/irrigation_hex_texture.png"));
		this.irrigation=new Model3D(irrigationHex);
		this.irrigation.setRotation(0, 30, 0);
		this.irrigation.setFlat();

		Model3D palaceHex=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/hex.obj"), 
				TextureFactory.getTexture("3Dobjects/default_hex_texture.png"));
		for (int i=0;i<palace.length;i++){
			Model3D model=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/palace"+((i+1)*2)+".obj"), 
					TextureFactory.getTexture("3Dobjects/palace"+((i+1)*2)+"_texture.png"));
//			Model3D number=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/"+((i+1)*2)+".obj"), 
//					TextureFactory.getTexture("3Dobjects/number_texture.png"));
			palace[i]=new Model3D(palaceHex,model);//,number);
			palace[i].setRotation(0, 30, 0);
			palace[i].setFlat();
		}
		
		Model3D highlandHex=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/highlands_empty_hex.obj"), 
				TextureFactory.getTexture("3Dobjects/highlands_empty_hex.png"));
		this.highland=new Model3D(highlandHex);
		this.highland.setRotation(0, 30, 0);
		this.highland.setFlat();

		Model3D lowlandHex=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/lowlands_empty_hex.obj"), 
				TextureFactory.getTexture("3Dobjects/lowlands_empty_hex.png"));
		this.lowland=new Model3D(lowlandHex);
		this.lowland.setRotation(0, 30, 0);
		this.lowland.setFlat();
		
		Model3D empty=ModelFactory.makeFromObj(MediaController.getInstance().getFile("3Dobjects/empty_hex.obj"), 
				TextureFactory.getTexture("3Dobjects/default_hex_texture.png"));
		this.ground=new Model3D(empty);
		this.ground.setRotation(0, 30, 0);
		this.ground.setFlat();
	}
	
	@Override
	public synchronized void hilightSpace(ArrayList<Direction> path, int height) {
		Vector2D offset=new Vector2D(0,0);
		for (Direction d:path){
			offset.translate(offsets[d.getIntValue()]);
		}
		Model3D newModel=hilight.clone();
		newModel.setTranslation(new Vector3D(offset.x, height*SPACE_HEIGHT, offset.y));
	}
	
	@Override
	public synchronized void displayDev(ArrayList<Direction> path, int height) {
		Vector2D offset=new Vector2D(0,0);
		for (Direction d:path){
			offset.translate(offsets[d.getIntValue()]);
		}
		Model3D newModel=developer.clone();
		newModel.setTranslation(new Vector3D(offset.x, height*SPACE_HEIGHT, offset.y));
	}

	@Override
	public synchronized void update() {
		spacecount=0;
		spaces.clear();
		ArrayList<Space> preTraversed=new ArrayList<Space>();
		updateRecursive(board.getRoot(), preTraversed, new Vector2D(0, 0));
		System.out.println("spacce count: "+spacecount);
		renderScene();
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
	
	
	int spacecount=0;
	/**adds model data for a given space.
	 * May be more than one Model3D.*/
	private void updateSpace(Space space, Vector2D offset){

		spacecount++;
		if (space.getHeight()==0){
			Model3D terrain=rice.clone();
			if (space.getSpaceGeography()==SpaceGeography.highlands){
				terrain=highland.clone();
			}
			if (space.getSpaceGeography()==SpaceGeography.lowlands){
				terrain=lowland.clone();
			}
			terrain.setTranslation(new Vector3D(offset.x, -SPACE_HEIGHT, offset.y));
			spaces.add(terrain);
		}
		
		for (int i=0;i<space.getHeight()-1;i++){
			Model3D newModel=buriedSpace.clone();
			newModel.setTranslation(new Vector3D(offset.x, i*SPACE_HEIGHT, offset.y));
			spaces.add(newModel);
		}
		if (space.getHeight()>0){
			//render the top tile
			Model3D newModel=buriedSpace.clone();
			newModel.setTranslation(new Vector3D(offset.x, space.getHeight()*SPACE_HEIGHT, offset.y));
			spaces.add(newModel);
		}
	}
	
	/**maybe, if we were doing a complicated model, I would feel the need
	 * to add a scene-graph system.  As it is, nothing we use would take
	 * advantage of it, so no scene graph has been made.*/
	public synchronized void renderScene(){
		
		//modelview doesn't translate lights
		glMatrixMode(GL11.GL_MODELVIEW);
		//reset the transformation matrix
		glLoadIdentity();
		//Creates an orthographic projection from 0,0 to CANVAS_WIDTH, CANVAS_HEIGHT
		glOrtho(-CANVAS_WIDTH/2, CANVAS_WIDTH/2, -CANVAS_HEIGHT/2, CANVAS_HEIGHT/2, CANVAS_NEAR, CANVAS_FAR);
		//take a guess what this method does.
		setLighting();
		//clear the background
		glClear(GL11.GL_COLOR_BUFFER_BIT);
		//if we want a skybox, we should put it here
		//SKYBOX!!!
		//clear depth buffers
		glClear(GL11.GL_DEPTH_BUFFER_BIT);
		
		//things too close will not be rendered
		glTranslated(0, 0, -(CANVAS_FAR+CANVAS_NEAR)/2);

		glRotated(scenePitch, 1, 0, 0);
		
		glRotated(sceneYaw, 0, 1, 0);
		
		glScaled(sceneScale, sceneScale, sceneScale);
		
		glTranslated(sceneTranslation.x, sceneTranslation.y, sceneTranslation.z);
		
		
		
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
	
	/**sets up the lighting for the scene*/
	private void setLighting(){
		float[] lightPos={0.5f,0f,1f,0f};
		
		//material colors
		float[] matspecular={1,1,1,0};
		float[] matambient={1,1,1,0};
		float[] matdiffuse={1f,1f,1f,0};
		
		//light colors
		float[] lightspecular={1f,1f,1f,1};
		float[] lightdiffuse={1f,1f,1f,0};
		float[] lightambient={0.2f,0.2f,0.2f,0};
		

		GL11.glEnable(GL11.GL_LIGHT0);
		
		ByteBuffer temp = ByteBuffer.allocateDirect(16);
		temp.order(ByteOrder.nativeOrder());
		FloatBuffer lightPosition=(FloatBuffer) temp.asFloatBuffer().put(lightPos).flip();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, (FloatBuffer)(lightPosition));
		
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, (FloatBuffer) temp.asFloatBuffer().put(lightspecular).flip());
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, (FloatBuffer) temp.asFloatBuffer().put(lightambient).flip());
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, (FloatBuffer) temp.asFloatBuffer().put(lightdiffuse).flip());
		
		//GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_EMISSION);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, (FloatBuffer) temp.asFloatBuffer().put(matspecular).flip());
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, (FloatBuffer) temp.asFloatBuffer().put(matdiffuse).flip());
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, (FloatBuffer) temp.asFloatBuffer().put(matambient).flip());
		
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 2f);
	}
	
	////////////////getters and setters////////////////
	
	public synchronized Vector3D getSceneTranslation() {
		return sceneTranslation;
	}

	public synchronized void setSceneTranslation(Vector3D sceneTranslation) {
		this.sceneTranslation = sceneTranslation;
	}

	public synchronized double getSceneYaw() {
		return sceneYaw;
	}

	public synchronized void setSceneYaw(double sceneYaw) {
		this.sceneYaw = sceneYaw;
	}

	public synchronized double getScenePitch() {
		return scenePitch;
	}

	public synchronized void setScenePitch(double scenePitch) {
		this.scenePitch = scenePitch;
	}

	public synchronized double getSceneScale() {
		return sceneScale;
	}

	public synchronized void setSceneScale(double sceneScale) {
		this.sceneScale = sceneScale;
	}
}