package view.commands.gameplayInput;

import view.commands.InputCommand;
import view.screens.gameplay.GameplayView;

// TODO verify is need
public abstract class GameplayInputCommand implements InputCommand {

    private GameplayView view;

    @Override
    public void execute() {
        view.update();
    }
}
