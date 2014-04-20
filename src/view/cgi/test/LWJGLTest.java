package view.cgi.test;

import java.awt.Canvas;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import view.cgi.Face3D;
import view.cgi.Model3D;
import view.cgi.ModelFactory;
import view.cgi.TextureFactory;
import view.cgi.TexturedFace3D;

public class LWJGLTest{

	public static final int RGB=3;
	public static final int RGBA=4;
	
	float pitch,yaw,roll;
	float apitch,ayaw,aroll;
	
	int defTexture=TextureFactory.MISSING_TEXTURE;
	int brokenTexture=TextureFactory.MISSING_TEXTURE;
	
	JFrame frame;
	JPanel parentPanel;
	Canvas cgiCanvas;
	
	Face3D mask;
	Face3D brokenFace;
	Model3D hills;
	
	public void start() throws LWJGLException {
		
		frame = new JFrame();
		frame.setSize(800, 800);
		
		parentPanel = new JPanel(false);
		parentPanel.setSize(frame.getSize());
		
		cgiCanvas = new Canvas();
		cgiCanvas.setSize(parentPanel.getSize());
		
		if (true){
			frame.add(parentPanel);
			parentPanel.add(cgiCanvas);
		}else{
			frame.add(cgiCanvas);
		}
		
		
		try {
			frame.setVisible(true);
			Display.setDisplayMode(new DisplayMode(100, 100));
			Display.setParent(cgiCanvas);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGL here

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_NORMALIZE);
		//enable face culling, so only the front of a shape is rendered
		GL11.glCullFace(GL11.GL_FRONT);
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		//GL11.glShadeModel(GL11.GL_SMOOTH);
		//GL11.glLight(light, pname, params)
		
		try {
			TextureFactory.loadMissingTexture("resources/imgs/Default.png");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		defTexture=TextureFactory.getTexture("resources/imgs/Fawkes.png");
		brokenTexture=TextureFactory.getTexture("this.file.does.not.exist.txt");
		
		mask=TexturedFace3D.MakeQuad(defTexture, -5, -5, 10, 10);
		brokenFace=TexturedFace3D.MakeQuad(brokenTexture, -5, -5, 10, 10);
		//hills=Model3D.makeFromObj(new File("resources/18665_Bobblehead_Lumberjack_v1.obj"));
		hills=ModelFactory.makeFromObj(new File("resources/hex.obj"),TextureFactory.getTexture("resources/imgs/GenericHex.png"));
		//hills.setSphere();
		
		while (!Display.isCloseRequested()) {

			pollInput();
			
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			
			
			//Creates an orthographic projection from -50,-50 to 50,50.
			//the depth range is 1 to 400
			GL11.glOrtho(-50, 50, -50, 50, 1, 400);
			
			setLighting();
			
			
			GL11.glClearColor(0.5f, 0, 0, 1);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			renderModel(hills,pitch,yaw,roll);
			GL11.glTranslatef(-20, 20, 0);
			renderFace(mask,apitch,0,0);
			GL11.glTranslatef(20, 0, 0);
			renderFace(mask,0,ayaw,0);
			GL11.glTranslatef(20, 0, 0);
			renderFace(mask,0,0,aroll);
			GL11.glTranslatef(-20, -40, 0);
			renderFace(brokenFace,apitch,ayaw,aroll);
			
			Display.update();
			
			apitch+=1;
			ayaw+=1;
			aroll+=1;
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Display.destroy();
	}

	
	int prevx=0;
	int prevy=0;
	boolean mouseWasDown=false;
	boolean rmouseWasDown=false;
	boolean mmouseWasDown=false;
	
	public void pollInput(){
		
		if (Mouse.isButtonDown(0)){
			if (mouseWasDown) {
				pitch += (Mouse.getY() - prevy);
				yaw += (Mouse.getX() - prevx);
			}
			prevy=Mouse.getY();
			prevx=Mouse.getX();
			mouseWasDown=true;
		}else{
			mouseWasDown=false;
		}
		
		if (Mouse.isButtonDown(2)){
			if (rmouseWasDown) {
				roll += (Mouse.getY() - prevy);
			}
			prevy=Mouse.getY();
			prevx=Mouse.getX();
			rmouseWasDown=true;
		}else{
			rmouseWasDown=false;
		}
		
		if (Mouse.isButtonDown(1)){
			if (mmouseWasDown) {
				hills.setTranslation(hills.getTranslation().translate((Mouse.getX()-prevx)*0.025f, (Mouse.getY()-prevy)*0.025f, 0));
			}
			prevy=Mouse.getY();
			prevx=Mouse.getX();
			mmouseWasDown=true;
		}else{
			mmouseWasDown=false;
		}
	}
	
	private void setLighting(){
		float[] lightPos={1f,1f,1f,0f};
		
		//material colors
		float[] matspecular={1,1,1,0};
		float[] matambient={1,1,1,0};
		float[] matdiffuse={0.5f,0.5f,0.5f,0};
		
		//light colors
		float[] lightspecular={1f,1f,1f,1};
		float[] lightdiffuse={0.5f,0.5f,0.5f,0};
		float[] lightambient={0.1f,0.1f,0.1f,0};
		

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
	
	/**renders a rectangle who'se front is understood to be in positive x
	 * Pitch, yaw, and roll are all in degreess*/
	public void renderFace(Face3D face, float pitch, float yaw, float roll){
		//you want to translate this object, but not the rest of the world,
		//so you push a new transformation matrix for this object, 
		//then pop it when you're done.
		GL11.glPushMatrix();
		
		GL11.glTranslatef(0, 0, -20);
		//GL11.glScalef(0.001f, 0.001f, 0.001f);
		
		//Rotate rotates an angle about a given vector
		//note that, by convention, x is positive to the right,
		//y is positive up, and z is positive towards the camera.
		GL11.glRotatef(yaw, 0, 1, 0);
		GL11.glRotatef(pitch, 0, 0, 1);
		GL11.glRotatef(roll, 1, 0, 0);
		
		face.render();
		
		GL11.glPopMatrix();
	}
	
	/**renders a rectangle who'se front is understood to be in positive x
	 * Pitch, yaw, and roll are all in degreess*/
	public void renderModel(Model3D face, float pitch, float yaw, float roll){
		//you want to translate this object, but not the rest of the world,
		//so you push a new transformation matrix for this object, 
		//then pop it when you're done.
		GL11.glPushMatrix();
		
		GL11.glTranslatef(0, 0, -20);
		GL11.glScalef(8, 8, 8);
		//GL11.glScalef(0.001f, 0.001f, 0.001f);
		
		//Rotate rotates an angle about a given vector
		//note that, by convention, x is positive to the right,
		//y is positive up, and z is positive towards the camera.
		face.setRotation(pitch, yaw, roll);
	
		face.render();
		
		GL11.glPopMatrix();
	}

	public static void main(String[] argv) throws LWJGLException {
		LWJGLTest displayExample = new LWJGLTest();
		displayExample.start();
	}
}
