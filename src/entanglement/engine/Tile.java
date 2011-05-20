package entanglement.engine;

import entanglement.utils.Config;

public class Tile {
	
	private int[] tileConf = null;
	private Path[] pathes = null;

	public Tile(){}
	
	public Tile(int[] tileConf){
		this.tileConf = tileConf;
		pathes = new Path[Config.inst().openingsPerSide * 2];
	}
	
	public int[] getTileConf() {
		return tileConf;
	}

	public void setTileConf(int[] tileConf) {
		this.tileConf = tileConf;
	}
	
	public void rotateClockWise(){
		int[] tmpConf = new int[tileConf.length];
		for (int i = 0;i < tileConf.length;i++)
		{
			if (i < tileConf.length - 2)
				tmpConf[i + Config.inst().openingsPerSide] = tileConf[i];
			else
				tmpConf[i - tileConf.length - 2] = tileConf[i];
		}
		
		tileConf = tmpConf;
	}
}
