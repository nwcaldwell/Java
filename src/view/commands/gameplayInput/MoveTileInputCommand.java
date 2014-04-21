// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.Direction;
import view.ViewController;

public class MoveTileInputCommand extends GameplayInputCommand {

    private Direction direction;

    public MoveTileInputCommand(ViewController viewController, Direction direction) {
        super(viewController);
        this.direction = direction;
    }

    @Override	public void doExecute() {
        Facade.getInstance().moveTile(direction);
	}
}