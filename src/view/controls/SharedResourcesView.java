package view.controls;

import models.board.SharedResources;
import models.palacefestival.Deck;
import view.MediaController;

import javax.swing.*;
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
        threeTiles = new JLabel();
        irrigationTiles = new JLabel();
        palace2Tiles = new JLabel();
        palace4Tiles = new JLabel();
        palace6Tiles = new JLabel();
        palace8Tiles = new JLabel();
        palace10Tiles = new JLabel();
        palaceDeck = new JButton();
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

        setLayout(festivalCard, 100, "palaceCard_1");
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

    public void setFestivalCardImage(String imageSource){
        System.out.println(String.format(source, imageSource));
        festivalCard.setIcon(new ImageIcon(MediaController.getInstance().getImage(String.format(source, imageSource))));
    }

    public void setNumPalaceCards(int num){
        palaceDeck.setText(""+num);
    }


    public void update(SharedResources resources, Deck deck) {
        setNumThreeTiles(resources.getNumThreeTiles());
        setNumIrrigationTiles(resources.getNumIrrigationTiles());
        setNumPalace2Tiles(resources.getNum2PalaceTiles());
        setNumPalace4Tiles(resources.getNum4PalaceTiles());
        setNumPalace6Tiles(resources.getNum6PalaceTiles());
        setNumPalace8Tiles(resources.getNum8PalaceTiles());
        setNumPalace10Tiles(resources.getNum10PalaceTiles());

        setFestivalCardImage(deck.getFestivalCard().toString());
        setNumPalaceCards(deck.getLibrary().size());
    }
}
