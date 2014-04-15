package view;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public abstract class View extends JPanel {

    private ViewController viewController;

    protected View( ViewController viewC ) {
        super(new BorderLayout());
        viewController = viewC;
    }

    protected ViewController getViewController() {
        return viewController;
    }
}
