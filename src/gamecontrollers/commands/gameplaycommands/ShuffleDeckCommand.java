// TODO developer [ Kevin ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;

import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.Deck;

public class ShuffleDeckCommand implements GameplayActionCommand {
    private Deck originalDeck;
    private Deck resultingDeck;

    public ShuffleDeckCommand(Deck original, Deck newDeck){

    }

	@Override	public void execute() {
		throw new UnsupportedOperationException();
	}
	@Override	public void undo() {
		throw new UnsupportedOperationException();
	}
	@Override	public void accept(CommandSaveVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}