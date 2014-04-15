// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class DrawCardFromDeckCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private PalaceCard card;

    public DrawCardFromDeckCommand(JavaPlayer p, PalaceCard c){
        this.player = p;
        this.card = c;
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