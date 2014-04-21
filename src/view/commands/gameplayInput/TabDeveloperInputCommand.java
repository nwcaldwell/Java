// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class TabDeveloperInputCommand extends GameplayInputCommand {

    public TabDeveloperInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        Facade.getInstance().tabThroughDevelopers();
	}
}