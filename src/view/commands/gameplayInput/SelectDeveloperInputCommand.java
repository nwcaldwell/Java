// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import models.board.HexDirection;
import view.ViewController;
import view.commands.JavaKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectDeveloperInputCommand extends GameplayInputCommand {

    //this is the set of keylisteners that will do stuff when stuff happens
    private List<JavaKeyListener> keySet;

    public SelectDeveloperInputCommand(ViewController viewController) {
        super(viewController);
        keySet = null;
    }


    @Override	public void doExecute() {
        Response response = Facade.getInstance().setupForMovingDeveloper();
        if ( response.hasErrors() ){
            // TODO tell view to show error
            // TODO probs by telling the view controller to show a pop up with the appropiate message
            getViewController().displayMessageToConsole(response);
        }
        else {
            getViewController().removeCurrentKeyListeners();
            getViewController().addKeyListeners(keySet);
        }
	}

    public void setKeySet(List<JavaKeyListener> newKeySet) {
        keySet = newKeySet;
    }

    public List<JavaKeyListener> getKeySet(){
        return keySet;
    }
}