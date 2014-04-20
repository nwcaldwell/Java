package gamecontrollers.rules.palacerules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.PalaceCommandCreator;
import gamecontrollers.turn.TurnController;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class PalaceLevelIsHigher extends PalaceRule {
    private PalaceCommandCreator creator;
    private TurnController controller;
    private Message message;

    public PalaceLevelIsHigher(PalaceCommandCreator creator, TurnController controller){
        this.creator = creator;
        this.controller = controller;
    }
    @Override
    public void update() {

    }

    @Override
    public Message getErrorMessage() {
        return null;
    }
}
