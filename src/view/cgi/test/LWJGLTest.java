package view.cgi.test;

import java.awt.Rectangle;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class LWJGLTest {

	float pitch,yaw,roll;
	float apitch,ayaw,aroll;
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(400, 400));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGL here

		while (!Display.isCloseRequested()) {

			pollInput();
			
			//switch to the projection matrix
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			//load the identity matrix (no transformations)
			GL11.glLoadIdentity();
			//Creates an orthographic projection from -50,-50 to 50,50.
			//the depth range is 1 to 400
			GL11.glOrtho(-50, 50, -50, 50, 1, 400);
			
			//enable face culling, so only the front of a shape is rendered
			GL11.glCullFace(GL11.GL_FRONT);
			GL11.glEnable(GL11.GL_CULL_FACE);
			
			pollInput();

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			renderRectangle(new Rectangle(-5, -5, 10, 10),pitch,yaw,roll);
			GL11.glTranslatef(-20, 20, 0);
			renderRectangle(new Rectangle(-5, -5, 10, 10),apitch,0,0);
			GL11.glTranslatef(20, 0, 0);
			renderRectangle(new Rectangle(-5, -5, 10, 10),0,ayaw,0);
			GL11.glTranslatef(20, 0, 0);
			renderRectangle(new Rectangle(-5, -5, 10, 10),0,0,aroll);
			
			Display.update();
			
			apitch+=1;
			ayaw+=1;
			aroll+=1;
			
			try {
				Thread.currentThread().sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Display.destroy();
	}
	
	
	int prevx=0;
	int prevy=0;
	boolean mouseWasDown=false;
	
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
	}
	
	/**renders a rectangle who'se front is understood to be in positive x
	 * Pitch, yaw, and roll are all in degreess*/
	public void renderRectangle(Rectangle r, float pitch, float yaw, float roll){
		//you want to translate this object, but not the rest of the world,
		//so you push a new transformation matrix for this object, 
		//then pop it when you're done.
		GL11.glPushMatrix();
		
		GL11.glTranslatef(0, 0, -200);
		
		//Rotate rotates an angle about a given vector
		//note that, by convention, x is positive to the right,
		//y is positive up, and z is positive towards the camera.
		GL11.glRotatef(yaw, 0, 1, 0);
		GL11.glRotatef(pitch, 0, 0, 1);
		GL11.glRotatef(roll, 1, 0, 0);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3f(1, 1, 1);
		
		//If memory serves, the face will be in your direction if
		//you order the vertexes clockwise
		GL11.glVertex2f(r.x, r.y+r.height);
		GL11.glVertex2f(r.x+r.width, r.y+r.height);
		GL11.glVertex2f(r.x+r.width, r.y);
		GL11.glVertex2f(r.x, r.y);
		
		//now to render the back
		GL11.glColor3f(0.5f, 0.5f, 0.5f);
		GL11.glVertex2f(r.x, r.y);
		GL11.glVertex2f(r.x+r.width, r.y);
		GL11.glVertex2f(r.x+r.width, r.y+r.height);
		GL11.glVertex2f(r.x, r.y+r.height);
		
		
		GL11.glEnd();
		
		GL11.glPopMatrix();
	}

	public static void main(String[] argv) {
		LWJGLTest displayExample = new LWJGLTest();
		displayExample.start();
	}
}
