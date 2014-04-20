package view.controls;

import gamecontrollers.Facade;
import models.palacefestival.JavaPlayer;
import view.MediaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.lang.reflect.Field;

//TODO [Sydney][Jorge]

public class PlayerView extends JPanel{
    private final int BORDER = 10;
    private final int WIDTH = (int)(3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - BORDER)/4 - BORDER*2;
    private final int HEIGHT = 250; //see GameplayView BORDER for number
    private final String source = "layout_%s.png";

    private JLabel playerName, actionPoints, famePoints, developers, twoTiles, riceTiles, villageTiles, actionTokens, palaceCards;
    private String colorString;
    private Color color;

    public PlayerView(String playerColor){
        colorString = playerColor.toLowerCase();
        color = convertStringToColor(colorString);

        playerName = new JLabel();
        actionPoints = new JLabel();
        famePoints = new JLabel();
        developers = new JLabel();
        twoTiles = new JLabel();
        riceTiles = new JLabel();
        villageTiles = new JLabel();
        actionTokens = new JLabel();
        palaceCards = new JLabel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        setNotPlayerTurn();

        initializeLayout();
    }

    private void initializeLayout(){
        playerName.setFont(new Font("Arial", 0, 18));
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setPreferredSize(new Dimension(WIDTH - 90, 25));
        playerName.setBorder(new EmptyBorder(5, 0, 5, 0));
        add(playerName);

        famePoints.setFont(new Font("Arial", 0, 30));
        famePoints.setHorizontalAlignment(SwingConstants.CENTER);
        famePoints.setPreferredSize(new Dimension(WIDTH/2 - BORDER*2, 35));
        famePoints.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        add(famePoints);

        actionPoints.setFont(new Font("Arial", 0, 30));
        actionPoints.setHorizontalAlignment(SwingConstants.CENTER);
        actionPoints.setPreferredSize(new Dimension(WIDTH/2 - BORDER*2, 35));
        actionPoints.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        add(actionPoints);
        disableActionPointLabel();

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setForeground(new Color(112, 102, 102));
        jSeparator1.setPreferredSize(new Dimension(WIDTH - BORDER, BORDER));
        add(jSeparator1);

        setLayout(developers, "developer_"+colorString);
        add(developers);

        setLayout(twoTiles, "two");
        add(twoTiles);

        setLayout(riceTiles ,"rice");
        add(riceTiles);

        setLayout(villageTiles, "village");
        add(villageTiles);

        setLayout(actionTokens, "token");
        add(actionTokens);

        setLayout(palaceCards, "card_back");
        add(palaceCards);
    }

    private void setLayout(JLabel label, String imageName){
        label.setPreferredSize(new Dimension(WIDTH/2 - BORDER, 45));
        label.setIcon(new ImageIcon(MediaController.getInstance().getImage(String.format(source, imageName))));
    }

    private Color convertStringToColor(String colorString){
        Color newColor = Color.black;
        try {
            Field field = Color.class.getField(colorString);
            newColor = (Color)field.get(null);
        } catch (Exception e) {
            System.out.println("There was an image parsing the color string to a color");
        }

        return newColor;
    }

    public void setPlayerTurn(){
        setBorder(new LineBorder(color, 2));
    }

    public void setNotPlayerTurn(){
        setBorder(new LineBorder(Color.BLACK, 2));
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

    public void update(JavaPlayer player) {
        //Facade.getInstance().getPlayer();
    }
}
