package models.board;

public class SharedResources {
    private int numThreeTiles, numIrrigationTiles;
    private int num2PalaceTiles;
    private int num4PalaceTiles, num6PalaceTiles, num8PalaceTiles, num10PalaceTiles;
	
	public SharedResources(int numThreeTiles, int numIrrigationTiles, int num2PalaceTiles, int num4PalaceTiles, int num6PalaceTiles, int num8PalaceTiles, int num10PalaceTiles){
		this.numThreeTiles = numThreeTiles;
		this.numIrrigationTiles = numIrrigationTiles;
		this.num2PalaceTiles = num2PalaceTiles;
		this.num4PalaceTiles = num4PalaceTiles;
		this.num6PalaceTiles = num6PalaceTiles;
		this.num8PalaceTiles = num8PalaceTiles;
		this.num10PalaceTiles = num10PalaceTiles;
	}

    public int getNumThreeTiles(){
        return numThreeTiles;
    }

    public int getNumIrrigationTiles() {
        return numIrrigationTiles;
    }

    public int getNum2PalaceTiles() {
        return num2PalaceTiles;
    }

    public int getNum4PalaceTiles() {
        return num4PalaceTiles;
    }

    public int getNum6PalaceTiles() {
        return num6PalaceTiles;
    }

    public int getNum8PalaceTiles() {
        return num8PalaceTiles;
    }

    public int getNum10PalaceTiles() {
        return num10PalaceTiles;
    }
}