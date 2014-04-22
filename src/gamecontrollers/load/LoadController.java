package gamecontrollers.load;

import view.MediaController;

import java.io.File;

/**
 * Created by jorgep on 4/14/14.
 */
public class LoadController {

    public LoadController(){

    }

    public void load(String fileName){
        File loadFile = MediaController.getInstance().getFile(fileName);
    }
}
