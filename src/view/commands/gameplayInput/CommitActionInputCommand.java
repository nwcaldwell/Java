// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class CommitActionInputCommand extends GameplayInputCommand {

    public CommitActionInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().commitMove();
    }
}