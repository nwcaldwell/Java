package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class TabPalaceCardInputCommand extends GameplayInputCommand {

    public TabPalaceCardInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void doExecute() {
        Facade.getInstance().tabPalaceCard();
    }
}