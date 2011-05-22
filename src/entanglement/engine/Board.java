package entanglement.engine;

import java.io.Reader;
import java.util.Iterator;
import java.util.Random;

import entanglement.utils.Config;
import entanglement.utils.Helper;

public class Board implements BoardInterface{
	
	Tile[] tiles;
	Tile currentTile;
	Player[] players;
	Player currentPlayer;
	int currentLocation;
	
	public Board(Reader reader) {
		Config.inst().load(reader);
		
		tiles = new Tile[Config.inst().boardWidth() * Config.inst().boardHeight()];
		currentTile = new Tile(Config.inst().tileConf((new Random()).nextInt(Config.inst().tileTypesCount())));
		
		players = new Player[Config.inst().playersCount()];
		for (int i = 0;i < Config.inst().playersCount();i++)
			players[i] = new Player(i);
		currentPlayer = players[0];
		
		currentLocation = Config.inst().startLocation() - Config.inst().boardWidth();
	}
	
	public boolean allGameOver(){
		for (int i = 0;i < Config.inst().playersCount();i++)
			if (players[i].getGameOver() == false)
				return false;
		
		return true;
	}
	
	private void updateGameOver(int player){
		Iterator<Path> i = players[player].getPaths().iterator();
		int tmpLocation = 0;
		
		tmpLocation = Helper.getOppositeLocation(Config.inst().startLocation(),players[player].ind());
		
		while (i.hasNext())
			tmpLocation = Helper.getOppositeLocation(tmpLocation,i.next().getEnd()/Config.inst().numberOfSides());
		
		if ((tmpLocation%Config.inst().boardWidth() == Config.inst().boardWidth()) || (tmpLocation%Config.inst().boardWidth() == -1))
		{
			players[player].setGameOver(true);
			return;
		}
		if ((tmpLocation/Config.inst().boardHeight() == Config.inst().boardHeight()) || (tmpLocation/Config.inst().boardHeight() == -1))
		{
			players[player].setGameOver(true);
			return;
		}
		
		for (int p = 0;p < Config.inst().playersCount();p++)
			if (p != players[player].ind())
				if(players[player].lastPaths().getEnd() == players[p].lastPaths().getEnd())
				{
					players[player].setGameOver(true);
					players[p].setGameOver(true);
					return;
				}
	}
	
	@Override
	public boolean fixTile() {
		int oppositeOpening = Helper.getOppositeOpening(currentPlayer.lastPaths().getEnd());
		
		if (currentTile.hasOpeningAt(oppositeOpening) == false)
			return false;
		
		Path choosenPath = currentTile.getPathFromOpening(oppositeOpening);
		if (choosenPath.getStart() == oppositeOpening)
			currentPlayer.addPath(new Path(choosenPath.getStart(),choosenPath.getEnd()));
		else
			currentPlayer.addPath(new Path(choosenPath.getEnd(),choosenPath.getStart()));
		updateGameOver(currentPlayer.ind());
		
		if (allGameOver() == false)
		{
			do
			{
				if (currentPlayer == players[Config.inst().playersCount()-1])
					currentPlayer = players[0];
				else
					currentPlayer = players[currentPlayer.ind() + 1];
			}while (currentPlayer.getGameOver());
			
			tiles[currentLocation] = currentTile;
			
			currentLocation = Helper.getOppositeLocation(currentLocation, currentPlayer.lastPaths().getEnd()/Config.inst().numberOfSides());
			
			currentTile = new Tile(Config.inst().tileConf((new Random()).nextInt(Config.inst().tileTypesCount())));
		}
		
		return true;
	}
	
	@Override
	public int getScore(int player) {
		if (player < 0)
			return -1;
		
		return players[player].getScore();
	}

	@Override
	public boolean isGameOver(int player) {
		return players[player].getGameOver();
	}

	@Override
	public boolean rotateTileAntiClockwise() {
		currentTile.rotateAntiClockWise();
		return true;
	}

	@Override
	public boolean rotateTileClockwise() {
		currentTile.rotateClockWise();
		return true;
	}

	@Override
	public boolean switchTile(int tileType) {
		if(tileType < 0 || tileType > Config.inst().tileTypesCount()-1)
			return false;
		
		currentTile.setTileConf(Config.inst().tileConf(tileType));
		return true;
	}
}
