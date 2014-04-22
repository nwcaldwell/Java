// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import java.util.List;

import gamecontrollers.Facade;
import models.board.Direction;
import view.ViewController;
import view.controls.BoardView;

public class MoveTileInputCommand extends GameplayInputCommand {

    private Direction direction;
    private BoardView boardView;

    public MoveTileInputCommand(ViewController viewController, BoardView boardview, Direction direction) {
        super(viewController);
        this.direction = direction;
        this.boardView=boardView;
    }

    @Override	public void doExecute() {
        Facade.getInstance().moveTile(direction);
        List<Direction> path = Facade.getInstance().getTilePlacementPath();
        boardView.update();
        boardView.addTiles(Facade.getInstance().getCurrentTileComponent(), path);
	}
}