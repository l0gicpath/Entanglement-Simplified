package entanglement.engine;

import java.util.*;

public class Player {

	private int index;
	private LinkedList<Path> pathes;
	
	public Player(int index){
		this.index = index;
		pathes = new LinkedList<Path>();
	}
	
	public int ind(){
		return index;
	}
	
	public void addPath(Path newPath){
		pathes.add(newPath);
	}
	
	public int getScore(){
		return pathes.size();
	}
}
