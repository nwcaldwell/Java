package models.board;

public class HexSpace extends Space<HexSpace, HexTile, HexDirection>{

	@Override
	public void placeTile(HexTile tile) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public HexSpace getAdjacentSpace(HexDirection direction) {
        return null;
    }

    @Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean verifyStacking(HexTile tile) {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public boolean neighborExists(HexDirection direction) {
        return false;
    }

    @Override
	public boolean verifyHeights(HexTile tile) {
		// TODO Auto-generated method stub
		return false;
	}

}
