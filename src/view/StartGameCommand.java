package view;

import view.commands.NavCommand;
import view.screens.NewGameView;

/**
 * Created by jorgep on 4/19/14.
 */
public class StartGameCommand extends NavCommand {

    private NewGameView newGameView;

    public StartGameCommand(ViewController viewC, View view, NewGameView nGameView) {
        super(viewC, view);
        newGameView = nGameView;
    }

    public void execute() {

    }

}
