package gamecontrollers.rules.palacerules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.PalaceCommandCreator;
import gamecontrollers.turn.TurnController;

/*
    THIS ISNT SUPER NEEDED IM PRETTY SURE
    LOGIC DELEGATED TO PALACES COMBINED WITH
    TARGETHASPROPERZONING
 */

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
