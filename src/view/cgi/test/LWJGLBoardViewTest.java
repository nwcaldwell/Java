package view.cgi.test;

import javax.swing.JFrame;

import org.lwjgl.input.Mouse;

import view.ViewController;
import view.cgi.LWJGLBoardView;
import view.cgi.Vector2D;
import view.cgi.Vector3D;

import models.board.Board;
import models.board.HexDirection;

public class LWJGLBoardViewTest {

	JFrame frame = new JFrame();
	Board board;
	LWJGLBoardView view;
	
	public LWJGLBoardViewTest() {
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board=new Board(HexDirection.N, "board.txt");
		ViewController viewController=new ViewController();
		view = new LWJGLBoardView(board, viewController);
		view.setSize(512,512);
		System.out.println(frame.isDisplayable());
		frame.add(view);
		
		while (true){
			view.update();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	int x=0,y=0;
	int pitch=90,yaw=0;
	
	int prevx=0;
	int prevy=0;
	boolean mouseWasDown=false;
	boolean rmouseWasDown=false;
	boolean mmouseWasDown=false;
	
	int zoom=100;
	
	public void pollInput(){
		
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
				  (float)(x*LWJGLBoardView.CANVAS_WIDTH/(frame.getWidth()))
				, -(float)(y*LWJGLBoardView.CANVAS_HEIGHT/(frame.getHeight())));
		sceneTranslation=sceneTranslation.rotate((float) ((yaw*Math.PI)/180f));
		
		view.setSceneTranslation(new Vector3D(
				sceneTranslation.x,
				0,
				sceneTranslation.y
				));
		view.setScenePitch(pitch);
		view.setSceneYaw(yaw);
	}
	
	public static void main(String[] args) {
		LWJGLBoardViewTest tester=new LWJGLBoardViewTest();
	}
	
}
