package view.controls;

import javax.swing.*;
import java.awt.*;

//TODO [Sydney][Jorge]

public class ConsoleView extends JPanel {
    private JTextField consoleLog;
    private StringBuilder gameBacklog;

    public ConsoleView() {

        consoleLog = new JTextField();
        gameBacklog = new StringBuilder();
        updateText("Console created, this is a placeholder.");

        //set the size
        setMinimumSize(new Dimension()); //TODO
    }

    public void updateText(String alert) {
        gameBacklog.append("\n"+alert);
    }
}
