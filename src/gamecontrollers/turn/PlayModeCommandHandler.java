package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;

/**
 * Created by kevinnieman on 4/15/14.
 */
public class PlayModeCommandHandler implements CommandHandler {
    private HistoryChannelController history;

    public PlayModeCommandHandler(HistoryChannelController history){
        this.history = history;
    }

    public void handleCommand(GameplayActionCommand gac) {
        gac.execute();
        history.addCommand(gac);
    }



}
