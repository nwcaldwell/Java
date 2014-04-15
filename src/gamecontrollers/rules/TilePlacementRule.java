package gamecontrollers.rules;
import gamecontrollers.commandcreator.TileCommandCreator;

/**
 * Created by jorgep on 4/14/14.
 */
public class TilePlacementRule {
	TileCommandCreator tcc;

	TilePlacementRule(TileCommandCreator tcc)
	{
		this.tcc = tcc;
	}
	public void update()
	{
		throw new UnsupportedOperationException();

	}
	public String getErrorMessage()
	{
		throw new UnsupportedOperationException();
	}
}
