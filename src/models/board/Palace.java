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

    public int getLevel(){
        return level;
    }

    public int getTiePoints(){
        return Math.round(level/4);
    }

    public int getPoints(){
        return Math.round(level/2);
    }

    public void setHasHadFestival(boolean hasHadFestival) {
        this.faceUp = hasHadFestival;
    }

    public boolean hasHadFestival() {
        return faceUp;
    }
}
