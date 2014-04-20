package view.screens;

import models.Pair;
import view.StartGameCommand;
import view.View;
import view.ViewController;
import view.commands.JavaButtonListener;
import view.screens.gameplay.PlayView;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//TODO [Sydney][Jorge]

public class NewGameView extends View {

    private static final String BOARD_FILE_NAME = "board.txt";
    private JTextField[] playersNames;
    private JComboBox[] colorSelections;
    private JButton startGame;

    public NewGameView(ViewController viewC) {
        super(viewC);

        playersNames = new JTextField[4];
        colorSelections = new JComboBox[4];
        startGame = new JButton("Let's Play!");
        startGame.addActionListener(new JavaButtonListener(
                        new StartGameCommand(this.getViewController(),
                        new PlayView(this.getViewController()),
                        this)));

        initializeView();
    }

    private void initializeView(){
        JLabel title = new JLabel("New JavaGame");
        title.setFont(new Font("Arial", 0, 18));

        String[] colors = {"Red", "Yellow", "Green", "Blue"}; //TODO add these to the media controller

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(480, 500));
        container.add(title);

        JPanel[] playerColorContainers = new JPanel[4];
        for(int i = 0; i < playersNames.length; i++){
            playerColorContainers[i] = new JPanel();
            playerColorContainers[i].setMinimumSize(new Dimension(480, 70));

            playersNames[i] = new JTextField();
            playersNames[i].setPreferredSize(new Dimension(100, 25));

            colorSelections[i] = new JComboBox(colors);
            colorSelections[i].setSelectedIndex(i);

            playerColorContainers[i].add(new JLabel("Player " + (i + 1) + " name: "));
            playerColorContainers[i].add(playersNames[i]);
            playerColorContainers[i].add(colorSelections[i]);

            container.add(playerColorContainers[i]);
        }

        container.add(startGame);
        add(container, BorderLayout.CENTER);
    }

    public List<Pair<String,String>> getPlayersData() {

        List<Pair<String,String>> playersData = new ArrayList<Pair<String,String>>();

        for(int i = 0; i < playersNames.length; i++){
            if(!playersNames[i].getText().equals("")){
                playersData.add(
                        new Pair<String, String>(
                            playersNames[i].getText(),
                            colorSelections[i].getSelectedItem().toString()
                        )
                );
            }
        }

        return playersData;
    }

    public String getBoardFileName(){
       return BOARD_FILE_NAME;
    }
}
