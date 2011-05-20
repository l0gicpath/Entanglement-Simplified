package entanglement.engine;

import java.io.Reader;


public class Board implements BoardInterface{
	
	Tile tiles[];
	
	public Board(Reader reader) {}
	
	@Override
	public boolean fixTile() {
		return false;
	}

	@Override
	public int getScore(int player) {
		return 0;
	}

	@Override
	public boolean isGameOver(int player) {
		return false;
	}

	@Override
	public boolean rotateTileAntiClockwise() {
		return false;
	}

	@Override
	public boolean rotateTileClockwise() {
		return false;
	}

	@Override
	public boolean switchTile(int tileType) {
		return false;
	}
	
	
}
