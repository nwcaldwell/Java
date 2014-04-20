package view.cgi.test;

import javax.swing.JFrame;

import org.lwjgl.input.Mouse;

import view.ViewController;
import view.cgi.LWJGLBoardView;
import view.cgi.Vector3D;

import models.board.Board;
import models.board.HexDirection;

public class LWJGLBoardViewTest {

	JFrame frame = new JFrame();
	Board board;
	LWJGLBoardView view;
	ViewController viewController;
	
	public LWJGLBoardViewTest() {
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board=new Board(HexDirection.N, "resources/guessworkBoard.txt");
		viewController=new ViewController();
		view = new LWJGLBoardView(board);
		frame.add(view);
		view.setSize(512,512);
		try {
			view.initGL();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (true){
			view.update();
			view.renderScene();
			pollInput();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	int x=0,y=0;
	int pitch=45,yaw=45;
	
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
				y += (Mouse.getY() - prevy);
				x += (Mouse.getX() - prevx);
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
		view.setSceneTranslation(new Vector3D(
				  (float)(x*LWJGLBoardView.CANVAS_WIDTH/(frame.getWidth()*view.getSceneScale()))
				, (float)(y*LWJGLBoardView.CANVAS_HEIGHT/(frame.getHeight()*view.getSceneScale()))
				, 0));
		view.setScenePitch(pitch);
		view.setSceneYaw(yaw);
	}
	
	public static void main(String[] args) {
		LWJGLBoardViewTest tester=new LWJGLBoardViewTest();
	}
	
}
