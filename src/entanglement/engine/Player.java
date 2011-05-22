package entanglement.engine;

import java.util.*;

public class Player {

	private int index;
	private LinkedList<Path> paths;
	
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
	
	public int getScore(){
		return paths.size();
	}
}
