package view.cgi.test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class LWJGLTest {

	float pitch,yaw,roll;
	float apitch,ayaw,aroll;
	
	int defTexture=0;
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(400, 400));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGL here

		try {
			loadTexture();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(-1);
		}
		
		
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
	
	public void loadTexture() throws IOException{
		
		//load a texture to the graphics card
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, defTexture);
		//prepare to recieve image data from RGB integers
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		
		//set the texture to wrap top and bottom.
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		//give it a nice linear filter (looks bettter than nearest filtering for most things)
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		
		//modulate allows for shading
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		
		BufferedImage image = ImageIO.read(new File("resources/Default.png"));
		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0,
				image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth()
				* image.getHeight() * 3); // 4 for RGBA, 3 for RGB

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component.
															// Only for RGBA
			}
		}

		buffer.flip(); // FOR THE LOVE OF GOD DO NOT FORGET THIS
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, image.getWidth(), image.getHeight(), 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
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
