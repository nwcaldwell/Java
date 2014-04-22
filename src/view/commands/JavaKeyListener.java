package view.commands;

import view.commands.InputCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TODO [Jorge][Sydney]

public class JavaKeyListener implements KeyListener {

    private InputCommand command;
    private int keyCode;

    public JavaKeyListener( int key, InputCommand action ) {
        this.command = action;
        this.keyCode = key;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("button pressed");
        if(e.getKeyCode() == keyCode){
            System.out.println("Executing command associated with keycode: "+keyCode);
            command.execute();
        }
    }
}
