package entanglement.engine;

public interface BoardInterface {
	
	public int getScore(int player);
	public boolean isGameOver(int player);
	public boolean fixTile();
	public boolean switchTile(int tileType);
	public boolean rotateTileClockwise();
	public boolean rotateTileAntiClockwise();
}
