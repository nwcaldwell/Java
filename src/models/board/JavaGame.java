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
    private static final int PLAYERS_INITIAL_DEVELOPERS_COUNT = 12;
    private static final int PLAYERS_INITIAL_RICE_COUNT = 3;
    private static final int PLAYERS_INITIAL_VILLAGE_COUNT = 2;
    private static final int PLAYERS_INITIAL_TWO_SPACE_COUNT = 5;
    private static final int PLAYERS_INITIAL_EXTRA_ACTION_COUNT = 3;
    private static final int PLAYERS_INITIAL_CARDS_COUNT = 3;

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
        // TODO DETERMINE HOW TO MAKE THE PLAYERS DRAW INITIAL CARDS
        // TODO SO THAT IT CAN BE DUPLICATED BY THE LOAD
        deck = new Deck();
    }

    /*
    ========================================================================
      Public methods
    ========================================================================
    */

    public Deck getDeck(){
        return deck;
    }

    public SharedResources getSharedResources(){
        return sharedResources;
    }

    public JavaPlayer[] getPlayers(){
        return players;
    }

    public Board getBoard(){
        return board;
    }


    /*


    ========================================================================
      Private methods
    ========================================================================
    */
    private void initPlayers(List<Pair<String,String>> playersData) {


        players = new JavaPlayer[playersData.size()];

        for (int i = 0; i < playersData.size(); i++) {

            players[i] = new JavaPlayer(
                    playersData.get(i).getKey(),
                    playersData.get(i).getValue(),
                    PLAYERS_INITIAL_FAME_POINTS,
                    PLAYERS_INITIAL_DEVELOPERS_COUNT,
                    PLAYERS_INITIAL_RICE_COUNT,
                    PLAYERS_INITIAL_VILLAGE_COUNT,
                    PLAYERS_INITIAL_TWO_SPACE_COUNT,
                    PLAYERS_INITIAL_EXTRA_ACTION_COUNT);
        }
    }

    /*
    ========================================================================
      Protected methods
    ========================================================================
    */


}