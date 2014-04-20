// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;
import view.commands.InputCommand;

public class PlayPalaceCardInputCommand extends InputCommand {

    public PlayPalaceCardInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void execute() {
        Facade.getInstance().playPalaceCard();
        getViewController().update();
    }
}