package entanglement.engine;

import entanglement.utils.Config;

public class Tile {
	
	private int[] tileConf = null;
	private Path[] pathes = null;

	public Tile(){}
	
	public Tile(int[] tileConf){
		this.tileConf = tileConf;
		pathes = new Path[Config.inst().opeingsPerSide() * 2];
	}
	
	public int[] getTileConf() {
		return tileConf;
	}

	public void setTileConf(int[] tileConf) {
		this.tileConf = tileConf;
	}
}
