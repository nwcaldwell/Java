package gamecontrollers.rules.tileplacementrules;

import models.board.TileComponentContent;
import models.board.TilePlacementVisitor;
import gamecontrollers.Message;
import gamecontrollers.commandcreator.TilePlacementCommandCreator;

/**
 * Created by jorgep on 4/15/14.
 */

public class LandTypeRestrictionsRule extends TilePlacementRule {
	/* This boolean array is used to determine the validity of Tile Placements as well
	 * as how many action points it should cost to move from one TileComponent to another.
	 * By convention:
	 * 
	 * Village = 0
	 * Rice = 1
	 * Palace = 2
	 * Irrigation = 3
	 * 
	 * The position of the row represents the TileComponent that is being placed 
	 * on underneath (in the case of TilePlacement), or the first TileComponent in the case
	 * of Developer movement. 
	 * 
	 * The position of the column represents the TileComponent that is being placed on top 
	 * (in the case of TilePlacement), or the second TileComponent in the case of Developer movement */
	
	public final boolean[][] validityChecker = {{true, true, true, false},
                      												{true, true, false, false},
                      												{false, false, true, false},
                      										   	{false, false, false, false}};
                      	
	private TilePlacementCommandCreator tpc;
	private Message message;
	
	public LandTypeRestrictionsRule(TilePlacementCommandCreator tpc) {
	  this.tpc = tpc;
	}
	
	@Override
	public void update() {
		TilePlacementVisitor tpvUnder = new TilePlacementVisitor();
		TilePlacementVisitor tpvTop = new TilePlacementVisitor();
		
		TileComponentContent under = tpc.getCurrentSpace().getTile().getTileComponentContent();
		TileComponentContent top = tpc.getCurrentTile().getTileComponentContent();
		
		under.accept(tpvUnder);
		top.accept(tpvUnder);

		setValidity(validityChecker[tpvUnder.getValue()][tpvTop.getValue()]);
		
		TileComponentContent[] tileComponentArray = {under, top};
		
		if (!this.getValidity()) {
			message = new Message("Not allowed to place a %s on a &s", tileComponentArray, true);
		}
		
		else {
			message = new Message("Tile placed successfully", false);
		}
	}

	@Override
	public Message getErrorMessage() {
		return message;
	}
}
