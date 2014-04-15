package view.screens;

import view.View;
import view.ViewController;
import view.commands.NavCommand;
import view.commands.JavaButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO [Sydney][Jorge]

public class MainMenuView extends View {
    private JButton newGameButton, loadGameButton, quitGameButton;

    public MainMenuView(ViewController viewC) {
        super(viewC);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new JavaButtonListener( new NavCommand(this.getViewController(), new NewGameView(this.getViewController()))));

        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new JavaButtonListener(new NavCommand(this.getViewController(), new LoadGameView(this.getViewController()))));

        quitGameButton = new JButton("Quit Game");
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        initializeLayout();
    }

    private void initializeLayout(){
        JPanel buttons = new JPanel();
        buttons.setMinimumSize(new Dimension(150, 300));

        buttons.add(newGameButton);
        buttons.add(loadGameButton);
        buttons.add(quitGameButton);

    }
}

