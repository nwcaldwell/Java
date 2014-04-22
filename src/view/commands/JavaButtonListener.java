package view.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JavaButtonListener implements ActionListener {

    private final InputCommand inputCommand;

    public JavaButtonListener(InputCommand command){
        inputCommand = command;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        inputCommand.execute();
    }
}
