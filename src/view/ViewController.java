package view;

import view.screens.MainMenuView;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    private static final int  WINDOW_WIDTH = 800;
    private static final int  WINDO_HEIGHT = 800;
    private View currentView;
    private JFrame gameWindow;
    private List<JavaKeyListener> currentListeners;
    private MediaController mediaController;

    public ViewController() {

        currentView = null;
        currentListeners = new ArrayList<JavaKeyListener>();
        mediaController = new MediaController();
        gameWindow = new JFrame();
        gameWindow.setSize( WINDOW_WIDTH, WINDO_HEIGHT );
        gameWindow.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        gameWindow.setVisible( true );
    }

    public void start() {
       setCurrentView( new MainMenuView(this, mediaController));
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
