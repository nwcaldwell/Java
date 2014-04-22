package gamecontrollers.palacefestival;

import gamecontrollers.Response;
import gamecontrollers.commandcreator.FestivalCommandCreator;
import gamecontrollers.commands.gameplaycommands.PlayPalaceCardCommand;
import gamecontrollers.turn.TurnController;
import models.palacefestival.FestivalModel;
import models.palacefestival.PalaceCard;

import java.util.List;

/**
 * Created by ssyyddnneeyy on 4/19/14.
 */
public class FestivalCardController extends FestivalCommandCreator {
    private List<PalaceCard> palaceCards;
    private FestivalModel festivalModel;

    public FestivalCardController(List<PalaceCard> cards, FestivalModel model){
        reset(cards);
        festivalModel = model;
    }

    public void incrementCurrentCard(){
        festivalModel.incrementCurrentCard();
    }

    public void setTurnController(TurnController controller){
        //lol does nothing
    }

    @Override
    public PlayPalaceCardCommand getCommand() {
        return new PlayPalaceCardCommand(festivalModel.getCurrentPlayer(), festivalModel, festivalModel.getCurrentCard());
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public Response checkPossible() {   //TODO
        return null;
    }

    public void reset(List<PalaceCard> cards) {
        palaceCards = cards;
    }
}
