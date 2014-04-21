package gamecontrollers.rules.palacerules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.PalaceCommandCreator;
import gamecontrollers.turn.TurnController;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class CityIsLargeEnough extends PalaceRule {
    private PalaceCommandCreator creator;
    private TurnController controller;
    private Message message;

    public CityIsLargeEnough(PalaceCommandCreator creator, TurnController controller){
        this.creator = creator;
        this.controller = controller;
    }
    @Override
    public void update() {
        if(creator.getCurrentLevel() <= creator.getCitySize()){
            message = new Message("Necesito un palace-o mas grande", true);
            setValidity(false);
        }
        else{
            message = new Message("This palace is juuuuuust right", false);
            setValidity(true);
        }
    }

    @Override
    public Message getErrorMessage() {
        return message;
    }
}
