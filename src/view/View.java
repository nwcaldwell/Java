package view;

import gamecontrollers.Response;
import view.commands.JavaKeyListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

//TODO [Sydney][Jorge]

public abstract class View extends JPanel {
    /*
    ========================================================================
      Instance Variables
    ========================================================================
    */
    private ViewController viewController;
    protected List<JavaKeyListener> keyListeners;

    /*
    ========================================================================
      Constructors
    ========================================================================
    */
    protected View( ViewController viewC ) {
        super(new BorderLayout());
        viewController = viewC;
        keyListeners = new ArrayList<JavaKeyListener>();
    }

    /*
    ========================================================================
      Public Methods
    ========================================================================
    */
    // This method is called when the view is actually about to be displayed
    // on the screen
    public abstract void init();

    /*
    ========================================================================
      Protected Methods
    ========================================================================
    */
    protected ViewController getViewController() {
        return viewController;
    }

    protected int getScreenWidth(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    protected int getScreenHeight(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50;
    }

    public abstract void update();

    public List<JavaKeyListener> getJavaKeyListeners(){
        return keyListeners;
    }

    public abstract void displayResponseToConsole(Response response);
}
