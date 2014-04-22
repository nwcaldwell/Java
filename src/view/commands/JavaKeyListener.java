package view.commands;

import view.commands.InputCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TODO [Jorge][Sydney]

//public class JavaKeyListener implements KeyListener {
public class JavaKeyListener {

    private InputCommand command;
    private int keyCode;

    public JavaKeyListener( int key, InputCommand action ) {
        this.command = action;
        this.keyCode = key;
    }

    public void respondToKeyEvent(KeyEvent e) {
        if(e.getKeyCode() == keyCode){
            command.execute();
        }

    }
}
