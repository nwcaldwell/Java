package models.board;

import models.board.TileComponentContents.Palace;

public abstract class TileComponentContent implements VisitableTile {

    private boolean canAcceptPalace;

    public abstract boolean getWalkable();

    public abstract boolean canAcceptPalace(Palace p);

    public void setCanAcceptPalace(boolean bool){
        this.canAcceptPalace = bool;
    }

    protected boolean getCanAcceptPalace(){
        return canAcceptPalace;
    }

}
