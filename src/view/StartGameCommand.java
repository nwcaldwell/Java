package view;

import gamecontrollers.Facade;
import view.commands.NavCommand;
import view.screens.NewGameView;

public class StartGameCommand extends NavCommand {
    private NewGameView newGameView;

    public StartGameCommand(ViewController viewC, View view, NewGameView nGameView) {
        super(viewC, view);
        newGameView = nGameView;
    }

    @Override
    public void execute() {
        Facade.getInstance().startGame( newGameView.getPlayersData(), newGameView.getBoardFileName() );
        super.execute();
        super.updateViewController();
    }
}