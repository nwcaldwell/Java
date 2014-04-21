package gamecontrollers.rules.palacerules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.PalaceCommandCreator;
import gamecontrollers.turn.TurnController;
import models.board.TileComponentContents.Palace;

//Check that the palace can have the desired palace

/**
 * Created by kevinnieman on 4/20/14.
 */
public class TargetHasProperZoning extends PalaceRule{
    private PalaceCommandCreator creator;
    private TurnController controller;
    private Message message;

    public TargetHasProperZoning(PalaceCommandCreator creator, TurnController controller){
        this.creator = creator;
        this.controller = controller;
    }

    //check the tilecomponentcontent for if we can place it there
    @Override
    public void update() {
        if(creator.getSpace().getTileComponentContent().canAcceptPalace(new Palace(creator.getCurrentLevel()))){
            message = new Message("Palace is okay to be built here", false);
            setValidity(true);
        }
        else {
            message = new Message("Palace no puede buildar", true);
            setValidity(false);
        }
    }

    @Override
    public Message getErrorMessage() {
        return message;
    }

}
