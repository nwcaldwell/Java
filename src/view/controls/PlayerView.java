package view.controls;

import view.MediaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

//TODO [Sydney][Jorge]

public class PlayerView extends JPanel{
    private final int BORDER = 10;
    private final int WIDTH = (int)(3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - 25)/4 - BORDER*2;
    private final int HEIGHT = 250; //see GameplayView BORDER for number

    private JLabel playerName, actionPoints, famePoints, developers, twoTiles, riceTiles, villageTiles, actionTokens, palaceCards;

    public PlayerView(){
        playerName = new JLabel("Sydney");
        actionPoints = new JLabel("6 ap");
        famePoints = new JLabel("300");
        developers = new JLabel("12");
        twoTiles = new JLabel("3");
        riceTiles = new JLabel("5");
        villageTiles = new JLabel("4");
        actionTokens = new JLabel("3");
        palaceCards = new JLabel("3");

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        setBorder(new LineBorder(Color.BLACK, 2));
        setBackground(Color.WHITE);

        initializeLayout();
    }

    private void initializeLayout(){
        playerName.setFont(new Font("Arial", 0, 18));
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setPreferredSize(new Dimension(WIDTH - 90, 30));
        playerName.setBorder(new EmptyBorder(5, 0, 5, 0));
        add(playerName);

        famePoints.setFont(new Font("Arial", 0, 30));
        famePoints.setHorizontalAlignment(SwingConstants.CENTER);
        famePoints.setPreferredSize(new Dimension(WIDTH/2 - BORDER*2, 40));
        famePoints.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        add(famePoints);

        actionPoints.setFont(new Font("Arial", 0, 30));
        actionPoints.setHorizontalAlignment(SwingConstants.CENTER);
        actionPoints.setPreferredSize(new Dimension(WIDTH/2 - BORDER*2, 40));
        actionPoints.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        add(actionPoints);

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setForeground(new Color(112, 102, 102));
        jSeparator1.setPreferredSize(new Dimension(WIDTH - BORDER, BORDER));
        add(jSeparator1);

        setLayout(developers);
        add(developers);

        setLayout(twoTiles);
        add(twoTiles);

        setLayout(riceTiles);
        add(riceTiles);

        setLayout(villageTiles);
        add(villageTiles);

        setLayout(actionTokens);
        add(actionTokens);

        setLayout(palaceCards);
        add(palaceCards);
    }

    private void setLayout(JLabel label){
        label.setPreferredSize(new Dimension(WIDTH/2 - BORDER, 50));
        //label.setIcon(icon);
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

    public void enableActionPointLabel(){
        actionPoints.setVisible(true);
    }

    public void disableActionPointLabel(){
        actionPoints.setVisible(false);
    }
}
