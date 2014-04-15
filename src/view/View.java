package view;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public abstract class View extends JPanel {

    private MediaController mediaController;

    protected View( ViewController viewC, MediaController mediaC ) {
        super(new BorderLayout());
        mediaController = mediaC;
    }

    protected MediaController getMediaController() {
        return mediaController;
    }
}
