package view;

import view.commands.Command;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JavaKeyListener implements KeyListener {

    private Command command;
    private int keyCode;

    public JavaKeyListener( int key, Command action ) {
        this.command = action;
        this.keyCode = key;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {

        if ( e.getKeyCode() == keyCode ) {
            command.execute();
        }
    }
}
