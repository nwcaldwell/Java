package view.controls;

import view.MediaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//TODO [Sydney] [Jorge]

public class SharedResourcesView extends JPanel {
    private final int BORDER = 10;
    private final int WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/ 4 - BORDER/2);
    private final int HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 300 - BORDER*2);
    private final String source = "layout_%s.png";

    private JLabel threeTiles, irrigationTiles;
    private JLabel palace2Tiles, palace4Tiles, palace6Tiles, palace8Tiles, palace10Tiles;
    private JButton palaceDeck, festivalCard;
    private JLabel actionSummaryCard;

    public SharedResourcesView(){
        initLayout();
    }

    public void initLayout() {
        threeTiles = new JLabel("150");
        irrigationTiles = new JLabel("10");
        palace2Tiles = new JLabel("6");
        palace4Tiles = new JLabel("7");
        palace6Tiles = new JLabel("8");
        palace8Tiles = new JLabel("9");
        palace10Tiles = new JLabel("10");
        palaceDeck = new JButton("30");
        festivalCard = new JButton();
        actionSummaryCard = new JLabel(); //tODo

        //setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        initializeLayout();
    }

    private void initializeLayout(){
        setLayout(threeTiles, 100, "three");
        add(threeTiles);

        setLayout(irrigationTiles, 100, "irrigation");
        add(irrigationTiles);

        setLayout(palaceDeck, 100, "card_back");
        add(palaceDeck);

        setLayout(festivalCard, 100, "card_drum");
        add(festivalCard);

        setLayout(palace2Tiles, 75, "palace_2");
        add(palace2Tiles);

        setLayout(palace4Tiles, 75, "palace_4");
        add(palace4Tiles);

        setLayout(palace6Tiles, 75, "palace_6");
        add(palace6Tiles);

        setLayout(palace8Tiles, 75, "palace_8");
        add(palace8Tiles);

        setLayout(palace10Tiles, 75, "palace_10");
        add(palace10Tiles);
    }

    private void setLayout(JLabel comp, int height, String imageName){
        comp.setPreferredSize(new Dimension(WIDTH/5 - BORDER, height));
        comp.setHorizontalTextPosition(SwingConstants.CENTER);
        comp.setVerticalTextPosition(SwingConstants.BOTTOM);
        comp.setVerticalAlignment(SwingConstants.BOTTOM);
        comp.setIcon(new ImageIcon(MediaController.getInstance().getImage(String.format(source, imageName))));
    }

    private void setLayout(JButton comp, int height, String imageName){
        comp.setPreferredSize(new Dimension(WIDTH/4 - BORDER, height));
        comp.setHorizontalTextPosition(SwingConstants.CENTER);
        comp.setVerticalTextPosition(SwingConstants.BOTTOM);
        comp.setVerticalAlignment(SwingConstants.BOTTOM);
        comp.setIcon(new ImageIcon(MediaController.getInstance().getImage(String.format(source, imageName))));
    }

    /* TEXT SETTERS */
    public void setNumThreeTiles(int num){
        threeTiles.setText(""+num);
    }

    public void setNumIrrigationTiles(int num){
        irrigationTiles.setText(""+num);
    }

    public void setNumPalace2Tiles(int num){
        palace2Tiles.setText(""+num);
    }

    public void setNumPalace4Tiles(int num){
        palace4Tiles.setText(""+num);
    }

    public void setNumPalace6Tiles(int num){
        palace6Tiles.setText(""+num);
    }

    public void setNumPalace8Tiles(int num){
        palace8Tiles.setText(""+num);
    }

    public void setNumPalace10Tiles(int num){
        palace10Tiles.setText(""+num);
    }

    public void setFestivalCardImage(ImageIcon icon){
        festivalCard.setIcon(icon);
    }

    public void setNumPalaceCards(int num){
        palaceDeck.setText(""+num);
    }


}
