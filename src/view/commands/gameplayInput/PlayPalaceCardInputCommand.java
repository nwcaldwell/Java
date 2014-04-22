// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class PlayPalaceCardInputCommand extends GameplayInputCommand {

    public PlayPalaceCardInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().playPalaceCard();

    }
}