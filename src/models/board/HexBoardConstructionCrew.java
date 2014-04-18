package models.board;

import view.MediaController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by williammacfarlane on 4/17/14.
 */
public class HexBoardConstructionCrew extends BoardConstructionCrew {
	static Direction dir = HexDirection.N;
	@Override
	public Space buildBoard(String boardFileName) {
		//BufferedReader br = new BufferedReader(new FileReader(boardFileName));
		ArrayList<ArrayList<Space>> grid = putBoardInGrid(boardFileName);
		Space root = connectedBoard(grid);
		return root;
	}

	Space connectedBoard(ArrayList<ArrayList<Space>> grid)
	{
		int numRows = grid.size();
		int numCols = grid.get(0).size();
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{

			}
		}
		throw new UnsupportedOperationException();
	}

	ArrayList<ArrayList<Space>> putBoardInGrid(String boardFileName)
	{
		ArrayList<ArrayList<Space>> grid = new ArrayList<ArrayList<Space>>();
		try{
			File f;
			//f = MediaController.getInstance().getFile(boardFileName);
			f = new File(boardFileName); //TODO: replace this line with the line above it when media controller is implemented
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			int lineNum = 0;
			while((line = br.readLine()) != null)
			{
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
