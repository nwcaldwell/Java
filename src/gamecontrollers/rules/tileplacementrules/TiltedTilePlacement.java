package gamecontrollers.rules.tileplacementrules;

import gamecontrollers.Message;
import gamecontrollers.commandcreator.TilePlacementController;

/**
 * Created by williammacfarlane on 4/14/14.
 */
public class TiltedTilePlacement extends TilePlacementRule {
	
	private TilePlacementController tpc;
	
	public TiltedTilePlacement(TilePlacementController tpc) {
		this.tpc = tpc;
	}
	
	@Override
	public void update() {

	}

	@Override
	public Message getErrorMessage() {
		return null;
	}
}
