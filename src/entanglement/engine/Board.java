package entanglement.engine;

import java.io.Reader;
import java.util.Random;

import entanglement.utils.Config;

public class Board implements BoardInterface{
	
	Tile tiles[];
	Tile currentTile;
	Player currentPlayer;
	
	public Board(Reader reader) {
		Config.getInstance().load(reader);
	}
	
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
		currentTile.setTileConf(Config.getInstance().tileConf(tileType));
		return true;
	}
}
