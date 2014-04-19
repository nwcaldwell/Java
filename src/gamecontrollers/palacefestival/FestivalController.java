package gamecontrollers.palacefestival;

import models.board.Space;
import models.palacefestival.FestivalModel;
import models.palacefestival.JavaPlayer;
import models.palacefestival.PalaceCard;

import java.util.ArrayList;

public class FestivalController {
    private FestivalModel festivalModel;
    private FestivalLogicController logicController;
    private FestivalTurnController turnController;

    public FestivalController() {
        logicController = new FestivalLogicController();
    }

    public ArrayList<JavaPlayer> calculateWinners(){
		return null;}

    public void endFestival(){}

    public void startFestival(JavaPlayer[] players, PalaceCard festivalCard, Space palace){

    }

    public void playCard(PalaceCard card){

    }
}