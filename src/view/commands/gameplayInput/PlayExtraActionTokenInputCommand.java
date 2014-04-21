// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class PlayExtraActionTokenInputCommand extends GameplayInputCommand {

    public PlayExtraActionTokenInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().playExtraActionToken();
    }
}