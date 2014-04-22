// developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class AskForTieFestivalInputCommand extends GameplayInputCommand {

    public AskForTieFestivalInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {
        Facade.getInstance().askForPalaceFestivalTie();
    }
}