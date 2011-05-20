package entanglement.engine;

import java.io.Reader;
import java.util.Random;

import entanglement.utils.Config;

public class Board implements BoardInterface{
	
	Tile tiles[];
	Tile currentTile;
	int currentLocation;
	Player currentPlayer;
	
	public Board(Reader reader) {
		Config.inst().load(reader);
		
		tiles = new Tile[Config.inst().boardWidth() * Config.inst().boardHeight()];
		currentTile = new Tile(Config.inst().tileConf((new Random()).nextInt(Config.inst().tileTypesCount())));
		currentPlayer = 0;
		currentLocation = Config.inst().startLocation() - Config.inst().boardWidth();
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
		currentTile.setTileConf(Config.inst().tileConf(tileType));
		return true;
	}
}
