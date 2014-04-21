package view.cgi;

import models.board.Board;
import models.board.Direction;
import view.controls.BoardView;

import java.util.ArrayList;

/**an implementation of BoardView that uses an LWJGL canvas*/
public class LWJGLBoardView extends BoardView{
	
	public static final float CANVAS_WIDTH=10;
	public static final float CANVAS_HEIGHT=10;
	public static final float CANVAS_FAR=101;
	public static final float CANVAS_NEAR=1;
	
	LWJGLBoardViewBackend backend;
	
	Thread graphicsThread;
	
	public LWJGLBoardView(Board b) {
		super(b);
	}
	
	@Override
	public synchronized void addNotify() {
		
	}
	
	@Override
	public synchronized void hilightSpace(ArrayList<Direction> path, int height) {
		backend.hilightSpace(path, height);
	}
	
	@Override
	public synchronized void displayDev(ArrayList<Direction> path, int height) {
		backend.displayDev(path, height);
	}

	@Override
	public synchronized void update() {
		backend.update();
	}
	
	////////////////getters and setters////////////////
	
	public synchronized Vector3D getSceneTranslation() {
		return backend.getSceneTranslation();
	}

	public synchronized void setSceneTranslation(Vector3D sceneTranslation) {
		backend.setSceneTranslation(sceneTranslation);
	}

	public synchronized double getSceneYaw() {
		return backend.getSceneYaw();
	}

	public synchronized void setSceneYaw(double sceneYaw) {
		backend.setSceneYaw(sceneYaw);
	}

	public synchronized double getScenePitch() {
		return backend.getScenePitch();
	}

	public synchronized void setScenePitch(double scenePitch) {
		backend.setScenePitch(scenePitch);
	}

	public synchronized double getSceneScale() {
		return backend.getSceneScale();
	}

	public synchronized void setSceneScale(double sceneScale) {
		backend.setSceneScale(sceneScale);
	}
}