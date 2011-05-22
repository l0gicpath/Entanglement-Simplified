package entanglement.engine;

import java.util.*;

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
	}
	
	public LinkedList<Path> getPaths(){
		return paths;
	}
	
	public int getScore(){
		return paths.size();
	}
	
	public Path lastPaths(){
		return paths.getLast();
	}
	
	public boolean getGameOver(){
		return gameOver;
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
}
