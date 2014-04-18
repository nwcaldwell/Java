package view.controls;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//TODO [Sydney] [Jorge]

public class SharedResourcesView extends JPanel {
    private final int BORDER = 10;
    private final int WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/ 4 - BORDER/2);
    private final int HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 300 - BORDER*2);
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
        setLayout(threeTiles, 100);
        add(threeTiles);

        setLayout(irrigationTiles, 100);
        irrigationTiles.setPreferredSize(new Dimension(WIDTH/4 + WIDTH/2 - BORDER*2, 100));
        irrigationTiles.setBorder(new EmptyBorder(0, WIDTH/2- BORDER, 0, 0));
        add(irrigationTiles);

        setLayout(palace2Tiles, 75);
        add(palace2Tiles);

        setLayout(palace4Tiles, 75);
        add(palace4Tiles);

        setLayout(palace6Tiles, 75);
        add(palace6Tiles);

        setLayout(palace8Tiles, 75);
        add(palace8Tiles);

        setLayout(palace10Tiles, 75);
        add(palace10Tiles);

        setLayout(palaceDeck, 100);
        add(palaceDeck);

        setLayout(festivalCard, 100);
        add(festivalCard);
    }

    private void setLayout(JLabel comp, int height){
        comp.setPreferredSize(new Dimension(WIDTH/4 - BORDER, height));
        comp.setHorizontalTextPosition(SwingConstants.CENTER);
        comp.setVerticalTextPosition(SwingConstants.BOTTOM);
        comp.setVerticalAlignment(SwingConstants.BOTTOM);
        //comp.setIcon(icon);
    }

    private void setLayout(JButton comp, int height){
        comp.setPreferredSize(new Dimension(WIDTH/4 - BORDER, height));
        comp.setHorizontalTextPosition(SwingConstants.CENTER);
        comp.setVerticalTextPosition(SwingConstants.BOTTOM);
        comp.setVerticalAlignment(SwingConstants.BOTTOM);
        //comp.setIcon(icon);
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
