package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;
import view.commands.InputCommand;

public class TabPalaceCardInputCommand extends InputCommand {

    public TabPalaceCardInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void execute() {
        Facade.getInstance().tabPalaceCard();
        getViewController().update();
    }
}