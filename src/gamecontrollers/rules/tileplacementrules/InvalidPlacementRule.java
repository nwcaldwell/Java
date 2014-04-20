package gamecontrollers.rules.tileplacementrules;


import models.board.TileComponentContent;
import models.board.TilePlacementVisitor;
import gamecontrollers.Message;
import gamecontrollers.commandcreator.TilePlacementController;

/**
 * Created by williammacfarlane on 4/14/14.
 */

public class InvalidPlacementRule extends TilePlacementRule {
	
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
	
	private TilePlacementController tpc;
	
	public InvalidPlacementRule(TilePlacementController tpc) {
		this.tpc = tpc;
	}
	
	@Override
	public void update() {
		TilePlacementVisitor tpvUnder = new TilePlacementVisitor();
		TilePlacementVisitor tpvTop = new TilePlacementVisitor();
		
		tpc.getCurrentSpace().getTile().getTileComponentContent().accept(tpvUnder);
		tpc.getCurrentTile().getTileComponentContent().accept(tpvUnder);

		setValidity(validityChecker[tpvUnder.getValue()][tpvTop.getValue()]);
	}

	@Override
	public Message getErrorMessage() {
		throw new UnsupportedOperationException();
}
