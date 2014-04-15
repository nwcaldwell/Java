package view.controls;

import view.MediaController;

import javax.swing.*;

//TODO [Sydney][Jorge]

public class ConsoleView extends JPanel {
    private MediaController mediaC;
    private JTextField consoleLog;
    private StringBuilder gameBacklog;

    public ConsoleView(MediaController media) {
        this.mediaC = media;
    }

    public void updateText(String alert) {

    }
}
