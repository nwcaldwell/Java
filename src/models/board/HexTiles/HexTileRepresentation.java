package models.board.HexTiles;

import models.board.Direction;
import models.board.TileComponent;
import models.board.TileRepresentation;

/**
 * Created by williammacfarlane on 4/20/14.
 */
public abstract class HexTileRepresentation extends TileRepresentation{
	public TileComponent buildTile(Direction d, TileRepresentation tileRepresentation)
	{
		return d.getPreferredTileConstructionCrew().buildTile(tileRepresentation);
	}
}
