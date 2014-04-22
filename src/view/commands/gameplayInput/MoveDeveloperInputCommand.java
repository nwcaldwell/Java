// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import models.board.Direction;
import view.ViewController;
import view.commands.JavaKeyListener;

import java.util.ArrayList;

public class MoveDeveloperInputCommand extends GameplayInputCommand {

    private Direction direction;

    public MoveDeveloperInputCommand(ViewController viewController, Direction direction) {
        super(viewController);
        this.direction = direction;
    }

    @Override
    public void doExecute() {
        Facade.getInstance().moveDeveloper(direction);

	}
}