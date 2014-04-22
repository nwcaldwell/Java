// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import view.ViewController;

public class RotateTileInputCommand extends GameplayInputCommand {

    public RotateTileInputCommand(ViewController viewController) {
        super(viewController);
    }

    @Override
    protected void doExecute() {

        Facade.getInstance().rotateCurrentTileComponent();
        super.updateViewController();
        getViewController().getBoardview().update();
        getViewController().getBoardview().addTiles(Facade.getInstance().getCurrentTileComponent(),Facade.getInstance().getTilePlacementPath());
    }
}