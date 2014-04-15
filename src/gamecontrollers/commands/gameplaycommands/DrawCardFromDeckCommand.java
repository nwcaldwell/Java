// TODO developer [ Jorge ], test [ Sydney ]
package view.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class DrawCardFromDeckCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private PalaceCard card;

	@Override	public void execute() {
		throw new UnsupportedOperationException();
	}
}