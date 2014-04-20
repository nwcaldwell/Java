package models.board;

import models.Pair;
import models.palacefestival.Deck;
import models.palacefestival.JavaPlayer;

import java.util.List;

public class JavaGame {

    /*
    ========================================================================
      Pieces Counts Constants
    ========================================================================
    */
    private static final int THREE_SPACE_TILES_INIT_COUNT = 56;
    private static final int IRRIGATION_TILES_COUNT = 16;
    private static final int LEVEL_2_PALACES_COUNT = 6;
    private static final int LEVEL_4_PALACES_COUNT = 7;
    private static final int LEVEL_6_PALACES_COUNT = 8;
    private static final int LEVEL_8_PALACES_COUNT = 9;
    private static final int LEVEL_10_PALACES_COUNT = 10;
    private static final int PLAYERS_INITIAL_FAME_POINTS = 0;
    private static final int PLAYERS_INITIAL_DEVELOPERS_COUNT = 0;

    /*
    ========================================================================
      Instance variables
    ========================================================================
    */
	private JavaPlayer[] players;
    private Board board;
    private SharedResources sharedResources;
    private Deck deck;

    /*
    ========================================================================
      CONSTRUCTORS
    ========================================================================
    */
    // The 'key' in the pair is the player name and the 'value'
    //
	public JavaGame(List<Pair<String,String>> playersData, String boardFile){

        initPlayers(playersData);
        board = new Board(HexDirection.N, boardFile);
        sharedResources = new SharedResources(
                THREE_SPACE_TILES_INIT_COUNT,
                IRRIGATION_TILES_COUNT,
                LEVEL_2_PALACES_COUNT,
                LEVEL_4_PALACES_COUNT,
                LEVEL_6_PALACES_COUNT,
                LEVEL_8_PALACES_COUNT,
                LEVEL_10_PALACES_COUNT);
        deck = new Deck();
    }

    /*
    ========================================================================
      Public methods
    ========================================================================
    */

    /*
    ========================================================================
      Private methods
    ========================================================================
    */
    private void initPlayers(List<Pair<String,String>> playersData) {

//        players = new JavaPlayer[playersData.size()];
//
//        for (int i = 0; i < playersData.size(); i++) {
//            players[i] = new JavaPlayer()
//        }
    }

    /*
    ========================================================================
      Protected methods
    ========================================================================
    */
    protected Deck getDeck(){
        return deck;
    }

}