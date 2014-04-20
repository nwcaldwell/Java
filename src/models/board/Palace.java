package models.board;

public class Palace extends NotWalkable {
    private int level;
    private boolean faceUp;

    public Palace(int level){
        this.level = level;
        this.faceUp = true;
    }

    //purely for undo
    //returns true if flip works
	public boolean flipIfDown(){
        if(!faceUp){
            faceUp = true;
            return true;
        }
        else
            return false;
    }

    //returns true if flip works
    public boolean flipIfUp(){
        if(faceUp){
            faceUp = false;
            return true;
        }
        else
            return false;
    }

	public void accept(TilePlacementVisitor v) {
		v.visit(this);
	}

    @Override
    public void accept(TileVisitor v) {

    }
}
