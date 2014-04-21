package view;

import view.commands.JavaKeyListener;
import view.screens.MainMenuView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

// TODO delete if we stick to using the toolkit class private static final int  WINDOW_WIDTH = 800;
// TODO delete if we stick to using the toolkit class private static final int  WINDO_HEIGHT = 800;
    private View currentView;
    private JFrame gameWindow;
    private List<JavaKeyListener> currentListeners;

    public ViewController() {

        currentView = null;
        currentListeners = new ArrayList<JavaKeyListener>();
        gameWindow = new JFrame();
    }

    public void start() {
        // This toolkit class allow us to get the size of the screen to go fullscreen
        Toolkit tk = Toolkit.getDefaultToolkit();
        // Setup the game window
        gameWindow.setUndecorated(true);
        gameWindow.setSize((int) tk.getScreenSize().getWidth(), (int) tk.getScreenSize().getHeight());
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);

        // set the game window to the main view
        setCurrentView( new MainMenuView(this));


        // TODO remove this keylistener when the real quit is implemented
        gameWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("GAME WINDOW "+e.getKeyChar());
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        gameWindow.setFocusTraversalKeysEnabled(false);


//        gameWindow.setContentPane(new JLabel( new ImageIcon( MediaController.getInstance().getImage("Default.png") ) ) );
        gameWindow.validate();

    }

    public void setCurrentView( View newView ) {

        // Remove the current listeners
        removeCurrentKeyListeners();

        // Change the current view
        currentView = newView;

        //get the listeners from the view
        currentListeners = currentView.getJavaKeyListeners();

        //reset the key listeners
        resetKeyActionListers(currentListeners);

        // Update the window
        currentView.init();
        gameWindow.setContentPane( currentView );
        gameWindow.validate();
    }

    public void update(){
        currentView.update();
    }

    public void resetKeyActionListers(final List<JavaKeyListener> newListeners){
        gameWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                for(JavaKeyListener listener : newListeners){
                    listener.respondToKeyEvent(e);
                }
            }
        });
    }

    public void removeCurrentKeyListeners() {
        KeyListener[] key = gameWindow.getKeyListeners();
        for(int i = 0; i < key.length; i++){
            gameWindow.removeKeyListener(key[i]);
        }
    }
}
