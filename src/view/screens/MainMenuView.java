package view.screens;

import gamecontrollers.Response;
import view.View;
import view.ViewController;
import view.commands.JavaButtonListener;
import view.commands.NavCommand;

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
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init(){
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

        JPanel buttons = new JPanel();
        buttons.setBorder(new EmptyBorder(getScreenHeight()/2, 0, 0, 0));

        buttons.add(newGameButton);
        buttons.add(loadGameButton);
        buttons.add(quitGameButton);

        add(buttons, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void displayResponseToConsole(Response response) {
        throw new UnsupportedOperationException();
    }
}

