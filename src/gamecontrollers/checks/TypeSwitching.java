package gamecontrollers.checks;

import models.board.TileComparisonVisitor;
import models.board.TileComponentContent;
import models.board.TilePlacementVisitor;

/**
 * Created by kevinnieman on 4/20/14.
 */
public class TypeSwitching {
    private TileComparisonVisitor checker;

    public TypeSwitching(){
        checker = new TileComparisonVisitor();
    }

    public boolean check(TileComponentContent t1, TileComponentContent t2){
        return !checker.compare(t1, t2);
    }

}
