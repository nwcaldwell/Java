// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import view.ViewController;

public class StartFestivalInputCommand extends GameplayInputCommand {

    public StartFestivalInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        // TODO refactor startFestival in Facade Facade.getInstance().startFestival();
        throw new UnsupportedOperationException();
	}
}