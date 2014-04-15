package view.screens;

import view.MediaController;
import view.View;
import view.ViewController;

import javax.swing.*;

public class MainMenuView extends View {
    private JButton newGameButton, loadGameButton, quitGameButton;

    public MainMenuView(ViewController viewC, MediaController mediaC) {
        super(viewC, mediaC);
    }
}

