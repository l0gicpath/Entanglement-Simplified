package entanglement.engine;

import java.util.*;

import entanglement.utils.Config;
import entanglement.utils.Helper;

public class Player {

	private int index;
	private LinkedList<Path> paths;
	private boolean gameOver;
	
	public Player(int index){
		this.index = index;
		paths = new LinkedList<Path>();
	}
	
	public int ind(){
		return index;
	}
	
	public void addPath(Path newPath){
		paths.add(newPath);
		updateGameOver();
	}
	
	public int getScore(){
		return paths.size();
	}
	
	public Path lastPaths(){
		return paths.getLast();
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	
	private void updateGameOver(){
		Iterator<Path> i = paths.iterator();
		int tmpLocation = 0;
		
		tmpLocation = Helper.getOppositeLocation(Config.inst().startLocation(),index);
		
		while (i.hasNext())
			tmpLocation = Helper.getOppositeLocation(tmpLocation,i.next().getEnd()/Config.inst().numberOfSides());
		
		if ((tmpLocation%Config.inst().boardWidth() == Config.inst().boardWidth()) || (tmpLocation%Config.inst().boardWidth() == -1))
		{
			gameOver = true;
			return;
		}
		if ((tmpLocation/Config.inst().boardHeight() == Config.inst().boardHeight()) || (tmpLocation/Config.inst().boardHeight() == -1))
		{
			gameOver = true;
			return;
		}
		
		//gameOver = true if colliding with the path of another player
	}
}
