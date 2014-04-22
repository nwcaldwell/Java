// TODO developer [ Kevin ], test [ Sydney ]
package view.commands.gameplayInput;

import gamecontrollers.Facade;
import gamecontrollers.Response;
import models.board.HexDirection;
import view.ViewController;
import view.commands.JavaKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SelectDeveloperInputCommand extends GameplayInputCommand {

    //this is the set of keylisteners that will do stuff when stuff happens
    private ArrayList<JavaKeyListener> keyListeners =  new ArrayList<JavaKeyListener>(){{
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD1, new MoveDeveloperInputCommand(getViewController(), HexDirection.SW)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD2, new MoveDeveloperInputCommand(getViewController(), HexDirection.S)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD3, new MoveDeveloperInputCommand(getViewController(), HexDirection.SE)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD7, new MoveDeveloperInputCommand(getViewController(), HexDirection.NW)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD8, new MoveDeveloperInputCommand(getViewController(), HexDirection.N)));
        add(new JavaKeyListener(KeyEvent.VK_NUMPAD9, new MoveDeveloperInputCommand(getViewController(), HexDirection.NE)));
    }};

    public SelectDeveloperInputCommand(ViewController viewController) {
        super(viewController);
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
            getViewController().addKeyListeners(keyListeners);
        }
	}
}