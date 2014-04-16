package view.controls;

import view.MediaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlayerView extends JPanel{
    private final int BORDER = 10;
    private final int WIDTH = (int)(3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - 25)/4 - BORDER*2;
    private final int HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4 - 25) - BORDER*2; //see GameplayView BORDER for number

    private JLabel playerName, actionPoints, famePoints, developers, twoTiles, riceTiles, villageTiles, actionTokens, palaceCards;

    public PlayerView(){
        playerName = new JLabel("Sydney");
        actionPoints = new JLabel("6");
        famePoints = new JLabel();
        developers = new JLabel();
        twoTiles = new JLabel();
        riceTiles = new JLabel();
        villageTiles = new JLabel();
        actionTokens = new JLabel();
        palaceCards = new JLabel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        initializeLayout();
    }

    private void initializeLayout(){
        add(playerName);
        add(actionPoints);
        add(famePoints);
        add(developers);
        add(twoTiles);
        add(riceTiles);
        add(actionTokens);
        add(palaceCards);
    }

    public void setPlayerName(String name){
        playerName.setText(name);
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
