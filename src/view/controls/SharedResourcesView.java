package view.controls;

import javax.swing.*;

//TODO [Sydney] [Jorge]

public class SharedResourcesView extends JPanel {
    private JLabel threeTiles, irrigationTiles;
    private JLabel palace2Tiles, palace4Tiles, palace6Tiles, palace8Tiles, palace10Tiles;

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



}
