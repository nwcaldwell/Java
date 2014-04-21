// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class SelectDeveloperInputCommand extends GameplayInputCommand {

    public SelectDeveloperInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override	public void doExecute() {
        if ( Facade.getInstance().setupForMovingDeveloper().hasErrors() ){
            // TODO tell view to show error
            // TODO probs by telling the view controller to show a pop up with the appropiate message
        }
	}
}