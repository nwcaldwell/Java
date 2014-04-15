package view.controls;

import view.MediaController;

import javax.swing.*;

//TODO [Sydney][Jorge]

public class PlayerView extends JPanel{

    private MediaController mediaC;
    private JLabel actionPoints, famePoints, developers, twoTiles, riceTiles, villageTiles, actionTokens, palaceCards;

    public PlayerView(MediaController media){
        this.mediaC = media;
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
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 431fd82980edfe8871d4a8071a9fd9a8273d3d52
=======

>>>>>>> ef6f00223008b65a7b3d5f04158d6385a31a382a
}
