package view.screens.gameplay;

import view.*;
import view.controls.ConsoleView;
import view.controls.BoardView;
import view.controls.PlayerView;
import view.controls.SharedResourcesView;

import java.util.List;

//TODO [Sydney][Jorge]

public class GameplayView extends View {

    private ConsoleView consoleView;
    private List<PlayerView> playerViews;
    private BoardView boardView;
    private SharedResourcesView sharedResourcesView;

    protected GameplayView(ViewController viewC, MediaController mediaC) {
        super(viewC, mediaC);
    }

    protected ConsoleView getConsoleView() {
        return consoleView;
    }

    protected List<PlayerView> getPlayerViews() {
        return playerViews;
    }

    protected BoardView getBoardView() {
        return boardView;
    }

    protected SharedResourcesView getSharedResourcesView() {
        return sharedResourcesView;
    }

    protected void addPlayerView( PlayerView playerView ) {
        playerViews.add( playerView );
    }
}
