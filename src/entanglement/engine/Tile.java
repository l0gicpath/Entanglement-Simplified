package entanglement.engine;

import java.util.HashMap;

public class Tile {
	
	private int[] tileConf = null;
	private HashMap pathes = null;

	public Tile(int[] tileConf){
		this.tileConf = tileConf;
		this.pathes = new HashMap();
	}
	
	public int[] getTileConf() {
		return tileConf;
	}

	public void setTileConf(int[] tileConf) {
		this.tileConf = tileConf;
	}
}
