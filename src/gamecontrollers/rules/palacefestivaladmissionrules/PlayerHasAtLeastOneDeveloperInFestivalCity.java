package gamecontrollers.rules.palacefestivaladmissionrules;

import gamecontrollers.BoardLogicController;
import gamecontrollers.Message;
import gamecontrollers.rules.palacefestivaladmissionrules.PalaceFestivalAdmissionRule;
import models.board.Space;
import models.palacefestival.JavaPlayer;

import java.util.List;

/**
 * Created by williammacfarlane on 4/15/14.
 */
public class PlayerHasAtLeastOneDeveloperInFestivalCity extends PalaceFestivalAdmissionRule {
    BoardLogicController board;
    JavaPlayer player;
    Message message;

    PlayerHasAtLeastOneDeveloperInFestivalCity(BoardLogicController b, JavaPlayer p){
        this.board = b;
        this.player = p;
    }

	@Override
	public void update() {
		//TODO get all the cities with palaces that have not been
        boolean canHoldFestival = true;
        if (canHoldFestival) {
            message = new Message("Eligible to hold a palace festival", false);
            setValidity(true);
        }

        else {
            message = new Message("Tile placed successfully", true);
            setValidity(false);
        }
	}

	@Override
	public Message getErrorMessage() {
		return message;
	}
}
