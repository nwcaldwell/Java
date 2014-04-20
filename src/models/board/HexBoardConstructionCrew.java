package models.board;

import view.MediaController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Pair;

/**
 * Created by williammacfarlane on 4/17/14.
 */

/*
	This is all new because we hadn't considered the challenge of supporting the creation of
	 boards composed of different shapes in our program.

	 In this implementation, we took advantage of the fact that hexagons can fit in a two-dimensional array with
	 the right math. We use this 2d array to enable each hex space to find out who its neighbors are.
 */
class HexBoardConstructionCrew extends BoardConstructionCrew{
	private static Direction dir = HexDirection.N;
	public Space buildBoard(String boardFileName) {
		//BufferedReader br = new BufferedReader(new FileReader(boardFileName));
		List<List<Space>> grid = putBoardInGrid(boardFileName);
		Space root = connectBoard(grid);
		return root;
	}
	private static boolean inBounds(int r, int c, int numRows, int numCols)
	{
		if(r < 0 || r >= numRows || c < 0 || c >= numCols)
			return false;
		return true;
	}
	private static boolean spaceExists(Space s)
	{
		return (s != null);
	}
	private Space connectBoard(List<List<Space>> grid)
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
				Space a = grid.get(r).get(c);
				if(!spaceExists(a))
					continue;
				Space[] neighbors = new Space[dir.numDirections()];
				for(int i = 0; i < rDelta.length; i++)
				{
					int rChanged = r + rDelta[i];
					int cChanged = c + cDelta[i];
					if(rChanged < 0 || cChanged < 0)
						continue;
					if(!inBounds(rChanged, cChanged, numRows, grid.get(rChanged%2).size()))
						continue;
					Space b = grid.get(rChanged).get(cChanged);
					if(spaceExists(b))
						neighbors[i] = b;
				}
				a.setNeighbors(neighbors);
			}
		}

		//Hexes should all reference each other at this point

		//Return the first space that exists
		for(int i = 0; i < grid.size(); i++)
			for(int j = 0; j < grid.get(i).size(); j++)
				if(spaceExists(grid.get(i).get(j)))
					return grid.get(i).get(j);

		throw new IllegalStateException();
	}

	private List<List<Space>> putBoardInGrid(String boardFileName)
	{

		List<List<Space>> grid = new ArrayList<List<Space>>();
		try{
			File f;
			f = MediaController.getInstance().getFile(boardFileName);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			int lineNum = 0;
			while((line = br.readLine()) != null)
			{
				grid.add(new ArrayList<Space>());
				int startingIndex = lineNum % 2;
				for(int j = startingIndex; j < line.length(); j += 2)
				{
					if(line.charAt(j) == '.')
						grid.get(lineNum).add(null);
					else if(line.charAt(j) == 'H')
						grid.get(lineNum).add(new Space(dir, SpaceGeography.highlands));
					else if(line.charAt(j) == 'L')
						grid.get(lineNum).add(new Space(dir, SpaceGeography.lowlands));
					else if(line.charAt(j) == 'x')
						grid.get(lineNum).add(new Space(dir, SpaceGeography.central));
					else
						throw new IllegalStateException("Input wasn't expected!");
				}
				++lineNum;
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}

		return grid;
	}
}