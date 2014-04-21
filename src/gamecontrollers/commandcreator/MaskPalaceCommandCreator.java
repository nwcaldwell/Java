package gamecontrollers.commandcreator;

import gamecontrollers.Response;
import gamecontrollers.commands.GameplayActionCommand;
import gamecontrollers.commands.gameplaycommands.PlacePalaceCommand;
import gamecontrollers.rules.Rule;
import gamecontrollers.rules.palacerules.PalaceRule;
import gamecontrollers.turn.TurnController;
import models.board.Direction;
import models.board.SharedResources;
import models.board.Space;
import models.palacefestival.JavaPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class MaskPalaceCommandCreator extends PalaceCommandCreator{
    /*
        IF CURRENT LEVEL IS -1 THEN THERE ARE NO PALACES
     */

    //currentLevel is an index in the palceLevel list
    private int currentLevel;
    private List<PalaceRule> rules;
    private Space desiredSpace;
    private SharedResources resources;
    private TurnController controller;
    //This is the list of the number of palaces of each level
    private List<Integer> palaceLevelCounter;
    //this is the list of the palace levels
    private List<Integer> palaceLevel;

    public MaskPalaceCommandCreator(TurnController controller, SharedResources resources){
        this.controller = controller;
        this.resources = resources;
        palaceLevelCounter = new ArrayList<Integer>();
        palaceLevel = new ArrayList<Integer>();

        //populate the list of integers
        palaceLevelCounter.add(resources.getNum2PalaceTiles());
        palaceLevel.add(2);
        palaceLevelCounter.add(resources.getNum4PalaceTiles());
        palaceLevel.add(4);
        palaceLevelCounter.add(resources.getNum6PalaceTiles());
        palaceLevel.add(6);
        palaceLevelCounter.add(resources.getNum8PalaceTiles());
        palaceLevel.add(8);
        palaceLevelCounter.add(resources.getNum10PalaceTiles());
        palaceLevel.add(10);
        //implement the current level
        currentLevel = 0;
    }


    @Override
    public GameplayActionCommand getCommand() {
        return new PlacePalaceCommand(controller, palaceLevel.get(currentLevel), desiredSpace, resources);
    }

    public void move(Direction direction){
        desiredSpace = desiredSpace.getAdjacentSpace(direction);
    }

    //Tab through only the remaining types of palaces
    public void tabThroughPalacesRemaining(){
        //only allow for selecting non zero palaces

        //this method calls itself so make sure there is no infinite loop
        if(palaceLevelCounter.size() >=0){
            //no palaces left so we done here boys
            //set currentLevel to error value
            currentLevel = -1;
        }
        else {
            //increment the pointer and mod it to allow for wrap around
            currentLevel = (currentLevel + 1) % palaceLevelCounter.size();

            //check that current level is nonzero
            if (palaceLevelCounter.get(currentLevel) >= 0) {
                //if no palaces left of this size remove it from eligible lists
                palaceLevelCounter.remove(currentLevel);
                palaceLevel.remove(currentLevel);

                //now increment again
                tabThroughPalacesRemaining();
            }
        }


    }

    @Override
    public int getCost() {
        //just chill guys it always costs one
        return 1;
    }

    /*
        This method will poll the current rules and then construct a response
        from them and return it to stuff
     */
    public Response checkPossible(){
        Response response = new Response();

        for(Rule rool : rules) {
            response.addMessage(rool.getErrorMessage());
        }
        return response;
    }


   /*
  ========================================================================
     PRIVATE METHODS
  ========================================================================
   */

    private void notifyRules(){
        for(Rule rool : rules){
            rool.update();
        }
    }
}
