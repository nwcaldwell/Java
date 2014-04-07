package view;

import javax.swing.*;

public abstract class View extends JPanel {

    private MediaController mediaController;

    protected View( ViewController viewC, MediaController mediaC ) {

        mediaController = mediaC;
    }

    protected MediaController getMediaController() {
        return mediaController;
    }
}
