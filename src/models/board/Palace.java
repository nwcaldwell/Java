package models.board;

public class Palace extends NotWalkable {
    private int level;
    private boolean hasHadFestival;

    public Palace(int level){
        this.level = level;
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
        this.hasHadFestival = hasHadFestival;
    }

    public boolean hasHadFestival() {
        return hasHadFestival;
    }
}
