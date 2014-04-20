package view.screens;

import view.View;
import view.ViewController;
import view.commands.NavCommand;
import view.commands.JavaButtonListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO [Sydney][Jorge]

public class MainMenuView extends View {
    private JButton newGameButton, loadGameButton, quitGameButton;

    public MainMenuView(ViewController viewC) {
        super(viewC);

        newGameButton = new JButton("New JavaGame");
        newGameButton.addActionListener(new JavaButtonListener( new NavCommand(this.getViewController(), new NewGameView(this.getViewController()))));

        loadGameButton = new JButton("Load JavaGame");
        loadGameButton.addActionListener(new JavaButtonListener(new NavCommand(this.getViewController(), new LoadGameView(this.getViewController()))));

        quitGameButton = new JButton("Quit JavaGame");
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
        buttons.setBorder(new EmptyBorder(getScreenHeight()/2, 0, 0, 0));

        buttons.add(newGameButton);
        buttons.add(loadGameButton);
        buttons.add(quitGameButton);

        add(buttons, BorderLayout.CENTER);

    }
}

