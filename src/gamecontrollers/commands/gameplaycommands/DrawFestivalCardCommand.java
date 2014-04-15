// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands.gameplaycommands;

import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.save.CommandSaveVisitor;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

public class DrawFestivalCardCommand implements GameplayActionCommand {
    private JavaPlayer player;
    private PalaceCard festivalCard;

    public DrawFestivalCardCommand(JavaPlayer p, PalaceCard fest){
        this.player = p;
        this.festivalCard = fest;
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