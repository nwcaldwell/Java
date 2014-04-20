package gamecontrollers.palacefestival;

import gamecontrollers.Response;
import gamecontrollers.commandcreator.FestivalCommandCreator;
import gamecontrollers.commands.gameplaycommands.PlayPalaceCardCommand;
import models.palacefestival.PalaceCard;

import java.util.List;

/**
 * Created by ssyyddnneeyy on 4/19/14.
 */
public class FestivalCardController extends FestivalCommandCreator {
    private List<PalaceCard> palaceCards;
    private PalaceCard currentCard;
    private FestivalTurnController festivalTurnController;


    public FestivalCardController(List<PalaceCard> cards, FestivalTurnController festivalTurnC){
        palaceCards = cards;
        currentCard = palaceCards.get(0);
        festivalTurnController = festivalTurnC;
    }

    public void incrementCurrentCard(){
        int index = palaceCards.indexOf(currentCard);
        currentCard = palaceCards.get((index+1) % palaceCards.size());
    }

    public PalaceCard getCurrentCard(){
        return currentCard;
    }


    @Override
    public PlayPalaceCardCommand getCommand() {
        return new PlayPalaceCardCommand(festivalTurnController.getCurrentPlayer(), currentCard);
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public Response checkPossible() {
        return null;
    }

    public void reset(List<PalaceCard> cards) {
    }
}
