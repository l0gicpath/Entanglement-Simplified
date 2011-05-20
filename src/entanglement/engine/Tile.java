package entanglement.engine;

import java.util.HashMap;

public class Tile {
	
	private int[] tileConf = null;
	private Path[] pathes = null;

	
	public Tile(){}
	
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
