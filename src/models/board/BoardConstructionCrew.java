package models.board;


import java.io.IOException;

/**
 * Created by williammacfarlane on 4/17/14.
 */
/*
	This is new because we hadn't considered the challenge of supporting the creation of
	 boards composed of different shapes in our program.
*/
public abstract class BoardConstructionCrew {
	public abstract Space buildBoard(String file);
}