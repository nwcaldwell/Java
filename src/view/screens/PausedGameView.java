package view.screens;

import gamecontrollers.Response;
import view.MediaController;
import view.View;
import view.ViewController;

//TODO [Sydney][Jorge]

public class PausedGameView extends View {

    public PausedGameView(ViewController viewC, MediaController mediaC) {
        super(viewC);
    }

    // This method is called when the view is actually about to be displayed
    // on the screen
    @Override
    public void init() {
        throw new UnsupportedOperationException();
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
