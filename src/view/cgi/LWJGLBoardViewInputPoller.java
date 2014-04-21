package view.cgi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.lwjgl.input.Mouse;

/**runs input checks for lwjgl board view.*/
public class LWJGLBoardViewInputPoller implements MouseListener, MouseMotionListener{

	int x=0,y=0;
	int pitch=90,yaw=0;
	
	int prevx=0;
	int prevy=0;
	boolean mouseWasDown=false;
	boolean rmouseWasDown=false;
	boolean mmouseWasDown=false;
	
	int zoom=100;
	
	LWJGLBoardView view;
	
	public LWJGLBoardViewInputPoller(LWJGLBoardView view){
		this.view=view;
	}
	
	public void run() {
		System.out.println("Polling input");
		
		int wheelDelta=Mouse.getDWheel();
		if (wheelDelta!=0){
			zoom=Math.max(1,zoom+wheelDelta/Math.abs(wheelDelta));
		}
		
		if (Mouse.isButtonDown(0)){
			if (mouseWasDown) {
				y += (Mouse.getY() - prevy)/view.getSceneScale();
				x += (Mouse.getX() - prevx)/view.getSceneScale();
			}
			prevy=Mouse.getY();
			prevx=Mouse.getX();
			mouseWasDown=true;
		}else{
			mouseWasDown=false;
		}
		
		if (Mouse.isButtonDown(1)){
			if (rmouseWasDown) {
				pitch += (Mouse.getY() - prevy);
				yaw += (Mouse.getX() - prevx);
			}
			prevy=Mouse.getY();
			prevx=Mouse.getX();
			rmouseWasDown=true;
		}else{
			rmouseWasDown=false;
		}

		view.setSceneScale((zoom*zoom*zoom*0.0000005));

		Vector2D sceneTranslation=new Vector2D(
				  (float)(x*LWJGLBoardView.CANVAS_WIDTH/(view.getWidth()))
				, -(float)(y*LWJGLBoardView.CANVAS_HEIGHT/(view.getHeight())));
		sceneTranslation=sceneTranslation.rotate((float) ((yaw*Math.PI)/180f));
		
		view.setSceneTranslation(new Vector3D(
				sceneTranslation.x,
				0,
				sceneTranslation.y
				));
		view.setScenePitch(pitch);
		view.setSceneYaw(yaw);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Do nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Do nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		prevy=e.getY();
		prevx=e.getX();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Do nothing
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// This should change things
		System.out.println("dragging");
		
		int dx=e.getX()-prevx;
		int dy=e.getY()-prevy;
		prevy=e.getY();
		prevx=e.getY();
		if (e.getButton()==MouseEvent.BUTTON1){
			y += (dy)/view.getSceneScale();
			x += (dx)/view.getSceneScale();
		} else if (e.getButton()==MouseEvent.BUTTON2){
			pitch += (Mouse.getY() - prevy);
			yaw += (Mouse.getX() - prevx);
		}
		
		Vector2D sceneTranslation=new Vector2D(
				  (float)(x*LWJGLBoardView.CANVAS_WIDTH/(view.getWidth()))
				, -(float)(y*LWJGLBoardView.CANVAS_HEIGHT/(view.getHeight())));
		sceneTranslation=sceneTranslation.rotate((float) ((yaw*Math.PI)/180f));
		
		view.setSceneTranslation(new Vector3D(
				sceneTranslation.x,
				0,
				sceneTranslation.y
				));
		

		view.setScenePitch(pitch);
		view.setSceneYaw(yaw);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Do nothing
	}

}
