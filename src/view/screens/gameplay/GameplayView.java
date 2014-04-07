package view.screens.gameplay;

import view.*;
import view.controls.ConsoleView;
import view.controls.MapView;
import view.controls.PlayerView;
import view.controls.SharedResourcesView;

import java.util.List;

public class GameplayView extends View {

    private ConsoleView consoleView;
    private List<PlayerView> playerViews;
    private MapView mapView;
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

    protected MapView getMapView() {
        return mapView;
    }

    protected SharedResourcesView getSharedResourcesView() {
        return sharedResourcesView;
    }

    protected void addPlayerView( PlayerView playerView ) {
        playerViews.add( playerView );
    }
}
