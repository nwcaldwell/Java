package gamecontrollers.turn;


import gamecontrollers.BoardLogicController;
import models.palacefestival.JavaPlayer;

public class FinalScoreCalculator {
    private BoardLogicController boardLogic;
    private JavaPlayer playerToScore;


    /*
  ========================================================================
     CONSTRUCTORS
  ========================================================================
   */
    public FinalScoreCalculator(JavaPlayer p, BoardLogicController bl){
        this.playerToScore = p;
        this.boardLogic = bl;
    }


    /*
 ========================================================================
    PUBLIC METHODS
 ========================================================================
  */

    public int calculateScore(){

        return 0;
    }

    public void setPlayer(JavaPlayer p){

    }
}
