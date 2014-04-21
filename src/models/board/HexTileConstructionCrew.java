package models.board;

import models.board.HexTiles.HexTileRepresentation;
import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Rice;
import models.board.TileComponentContents.Village;
import models.Pair;
import java.util.ArrayList;

/**
 * Created by williammacfarlane on 4/20/14.
 */
public class HexTileConstructionCrew extends TileConstructionCrew {

	private static Direction dir = HexDirection.N;

	public TileComponent buildTile(TileRepresentation tileRepresentation) {
		if(!(tileRepresentation instanceof HexTileRepresentation))
			throw new IllegalArgumentException();
		//BufferedReader br = new BufferedReader(new FileReader(boardFileName));
		ArrayList<ArrayList<TileComponent>> grid = putTileInGrid(tileRepresentation);
		TileComponent root = connectTile(grid);
		return root;
	}
	private static boolean inBounds(int r, int c, int numRows, int numCols)
	{
		if(r < 0 || r >= numRows || c < 0 || c >= numCols)
			return false;
		return true;
	}
	private static boolean tileComponentExists(TileComponent tc)
	{
		return (tc != null);
	}
	private TileComponent connectTile(ArrayList<ArrayList<TileComponent>> grid)
	{
		int numRows = grid.size();


		int[] rDelta = new int[dir.numDirections()];
		int[] cDelta = new int[dir.numDirections()];
		HexDirection hd = HexDirection.N;
		for(int i = 0; i < rDelta.length; i++){
			Pair<Integer, Integer> gridRep = HexDirectionTranslator.get(hd);
			rDelta[i] = gridRep.getKey();
			cDelta[i] = gridRep.getValue();
			hd = hd.rotate();
		}

		for(int r = 0; r < numRows; r++)
		{
			int numCols = grid.get(r).size();
			for(int c = 0; c < numCols; c++)
			{
				TileComponent a = grid.get(r).get(c);
				if(!tileComponentExists(a))
					continue;
				TileComponent[] neighbors = new TileComponent[dir.numDirections()];
				for(int i = 0; i < rDelta.length; i++)
				{
					int rChanged = r + rDelta[i];
					int cChanged = c + cDelta[i];
					if(rChanged < 0 || cChanged < 0)
						continue;
					if(!inBounds(rChanged, cChanged, numRows, grid.get(rChanged%2).size()))
						continue;
					TileComponent b = grid.get(rChanged).get(cChanged);
					if(tileComponentExists(b))
						neighbors[i] = b;
				}
				a.setSiblings(neighbors);
			}
		}

		//Hexes should all reference each other at this point

		//Return the first space that exists
		for(int i = 0; i < grid.size(); i++)
			for(int j = 0; j < grid.get(i).size(); j++)
				if(tileComponentExists(grid.get(i).get(j)))
					return grid.get(i).get(j);

		throw new IllegalStateException();
	}

	ArrayList<ArrayList<TileComponent>> putTileInGrid(TileRepresentation tileRepresentation)
	{
		ArrayList<ArrayList<TileComponent>> grid = new ArrayList<ArrayList<TileComponent>>();
		String[] stringRep = tileRepresentation.stringRepresentation();
		String line;
		int lineNum = 0;
		while(lineNum < stringRep.length)
		{
			line = stringRep[lineNum];
			grid.add(new ArrayList<TileComponent>());
			for(int j = 0; j < line.length(); j++)
			{
				if(line.charAt(j) == '.')
					grid.get(lineNum).add(null);
				else if(line.charAt(j) == 'I')
					grid.get(lineNum).add(new TileComponent(dir, new Irrigation()));
				else if(line.charAt(j) == 'V')
					grid.get(lineNum).add(new TileComponent(dir, new Village()));
//				else if(line.charAt(j) == 'P')
//					grid.get(lineNum).add(new TileComponent(dir, new Palace()));
				else if(line.charAt(j) == 'R')
					grid.get(lineNum).add(new TileComponent(dir, new Rice()));
				else
					throw new IllegalStateException("Input wasn't expected!");
			}
			++lineNum;
		}


		return grid;
	}

}
