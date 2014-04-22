// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.Direction;
import view.ViewController;
import view.controls.BoardView;

public class MoveTileInputCommand extends GameplayInputCommand {

    private Direction direction;
    private BoardView boardView;

    public MoveTileInputCommand(ViewController viewController, BoardView bv, Direction direction) {
        super(viewController);
        this.direction = direction;
        boardView = bv;
    }

    @Override	public void doExecute() {
        Facade.getInstance().moveTile(direction);

	}
}