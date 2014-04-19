package view.screens;

import view.View;
import view.ViewController;
import view.commands.InputCommand;
import view.commands.JavaButtonListener;
import view.commands.NavCommand;
import view.screens.gameplay.PlayView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//TODO [Sydney][Jorge]

public class NewGameView extends View {
    JTextField[] playersNames;
    JComboBox[] colorSelections;
    JButton startGame;

    public NewGameView(ViewController viewC) {
        super(viewC);

        playersNames = new JTextField[4];
        colorSelections = new JComboBox[4];
        startGame = new JButton("Let's Play!");
        startGame.addActionListener(new JavaButtonListener(new NavCommand(this.getViewController(), new PlayView(this.getViewController()))));
        //TODO How I start new game?

        initializeView();
    }

    private void initializeView(){
        JLabel title = new JLabel("New Game");
        title.setFont(new Font("Arial", 0, 18));

        String[] colors = {"Red", "Yellow", "Green", "Blue"}; //TODO add these to the media controller?

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

    public ArrayList<String> getPlayerInformation(){
        ArrayList<String> information = new ArrayList<String>();
        for(int i = 0; i < playersNames.length; i++){
            if(!playersNames[i].getText().equals("")){
                String info = playersNames[i].getText()+"/"+colorSelections[i].getSelectedItem();
                information.add(info);
            }
        }

        return information;
    }

}
