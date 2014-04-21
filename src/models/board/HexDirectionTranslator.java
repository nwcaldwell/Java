package models.board;

import models.Pair;

import java.util.HashMap;


/**
 * Created by williammacfarlane on 4/19/14.
 */

/*
	The proper way to do this would be to change our HexDirection enum to an abstract class that holds actual directions.
	Then, each direction could tell us its grid representation.

	Currently, our HexDirection enum functions both as a container of directions AND as a particular direction.
	This enum, as we just found, is not cohesive and exhibits little flexibility.

	This realization was made too late, unfortunately, and would have resulted in a massive overhaul (which we couldn't
	afford). On the upside, this issue has been quarantined to this class (and in effect HexBoardConstructionCrew), as they are the
	only two classes that rely on a grid representation of a board of hexagons.
 */
public class HexDirectionTranslator {
	private static HashMap<HexDirection,Pair<Integer, Integer>> hm = new HashMap<HexDirection, Pair<Integer, Integer>>(){{
												//     row, column
		put(HexDirection.N, new Pair<Integer, Integer>(-2, 0));
		put(HexDirection.NE, new Pair<Integer, Integer>(-1, 1));
		put(HexDirection.SE, new Pair<Integer, Integer>(1, 1));
		put(HexDirection.S, new Pair<Integer, Integer>(2, 0));
		put(HexDirection.SW, new Pair<Integer, Integer>(1, -1));
		put(HexDirection.NW, new Pair<Integer, Integer>(-1, -1));
	}};
	public static Pair<Integer, Integer> get(HexDirection hd)
	{
		return hm.get(hd);
	}
}
