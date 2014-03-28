package view.cgi.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LWJGLTest {

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGL here

		while (!Display.isCloseRequested()) {

			//display opengl here
			
			Display.update();
		}

		Display.destroy();
	}

	public static void main(String[] argv) {
		LWJGLTest displayExample = new LWJGLTest();
		displayExample.start();
	}
}
