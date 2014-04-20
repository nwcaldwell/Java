package view;


import view.commands.JavaKeyListener;
import view.screens.MainMenuView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

//TODO [Jorge][Sydney]

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
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });


//        gameWindow.setContentPane(new JLabel( new ImageIcon( MediaController.getInstance().getImage("Default.png") ) ) );
        gameWindow.validate();

    }

    public void setCurrentView( View newView ) {

        // Remove listeners of current view
        // This also includes the ones that weren't added in the initial change of view
        for ( JavaKeyListener listener : currentListeners ) {

            currentView.removeKeyListener( listener );
        }

        // Change the current view
        currentView = newView;

        // Add listeners of the new view
        for ( JavaKeyListener listener : currentListeners ) {

            currentView.addKeyListener(listener);
        }

        // Update the window
        gameWindow.setContentPane( currentView );
        gameWindow.validate();
    }

    public void addKeyListener( JavaKeyListener listener ) {

        currentListeners.add( listener );
        gameWindow.addKeyListener( listener );
    }

    public void addKeyListener(List<JavaKeyListener> listeners){
        for (JavaKeyListener listener : listeners){
            addKeyListener(listener);
        }
    }

    public void removeKeyListener ( JavaKeyListener listener ) {

        currentListeners.remove( listener );
        gameWindow.removeKeyListener( listener );
    }

    public void removeKeyListener(List<JavaKeyListener> listeners) {
        for (JavaKeyListener listener : listeners){
            removeKeyListener(listener);
        }
    }
}
