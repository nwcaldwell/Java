package view.controls;

import javax.swing.*;

//TODO [Sydney][Jorge]

public class PlayerView extends JPanel{
    private JLabel actionPoints, famePoints, developers, twoTiles, riceTiles, villageTiles, actionTokens, palaceCards;

    public PlayerView(){

    }

    public void setNumActionPoints(int num){
        actionPoints.setText(""+num);
    }

    public void setNumFamePoints(int num){
        famePoints.setText(""+num);
    }

    public void setNumDevelopers(int num){
        developers.setText(""+num);
    }

    public void setNumTwoTiles(int num){
        twoTiles.setText(""+num);
    }

    public void setNumRiceTiles(int num){
        riceTiles.setText(""+num);
    }

    public void setNumVillageTiles(int num){
        villageTiles.setText(""+num);
    }

    public void setNumActionTokens(int num){
        actionTokens.setText(""+num);
    }

    public void setNumPalaceCards(int num){
        palaceCards.setText(""+num);
    }

}
