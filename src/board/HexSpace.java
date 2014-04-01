package board;

public class HexSpace extends Space<HexSpace,HexTile>{

	@Override
	public HexSpace getAdjacent(int direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeTile(HexTile tile) {
		// TODO Auto-generated method stub
		
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
	public boolean veriyHeights(HexTile tile) {
		// TODO Auto-generated method stub
		return false;
	}

}
