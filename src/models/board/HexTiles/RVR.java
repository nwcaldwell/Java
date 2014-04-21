package models.board.HexTiles;

import models.board.HexTileConstructionCrew;
import models.board.TileComponent;

/**
 * Created by williammacfarlane on 4/20/14.
 */
public class RVR extends HexTileRepresentation {
	public String[] stringRepresentation()
	{
		String[] rep = {"R,R", ".V."};
		return rep;
	}
}
