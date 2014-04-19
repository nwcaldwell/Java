package gamecontrollers.turn;

import gamecontrollers.commands.GameplayActionCommand;

/**
 * Created by kevinnieman on 4/15/14.
 */
public interface CommandHandler {

    public void handleCommand(GameplayActionCommand gac);


}
