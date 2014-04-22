// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class AcceptTieRequestCommand extends GameplayInputCommand {

    public AcceptTieRequestCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().acceptTieRequest();
    }
}