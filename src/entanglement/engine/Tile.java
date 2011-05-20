package entanglement.engine;

import entanglement.utils.Config;

public class Tile {
	
	private int[] tileConf = null;
	private Path[] pathes = null;

	public Tile(){}
	
	public Tile(int[] tileConf){
		this.tileConf = tileConf;
		pathes = new Path[Config.inst().openingsPerSide() * 2];
		updatePathes();
	}
	
	public int[] getTileConf() {
		return tileConf;
	}

	public void setTileConf(int[] tileConf) {
		this.tileConf = tileConf;
	}
	
	private void updatePathes(){
		int currPath = 0;
		
		for (int i = 0;i < tileConf.length;i++)
			if (tileConf[i] > i)
			{
				pathes[currPath] = new Path(i,tileConf[i]);
				currPath++;
			}
	}
	
	public void rotateClockWise(){
		int[] tmpConf = new int[tileConf.length];
		for (int i = 0;i < tileConf.length;i++)
		{
			if (i < tileConf.length - Config.inst().openingsPerSide())
				tmpConf[i + Config.inst().openingsPerSide()] = tileConf[i];
			else
				tmpConf[i - tileConf.length - Config.inst().openingsPerSide()] = tileConf[i];
		}
		
		this.tileConf = tmpConf;
		updatePathes();
	}
	
	public void rotateAntiClockWise() {
		int[] tmpConf = new int[tileConf.length];
		for (int i = tileConf.length - 1;i >= 0;i--) {
			if (i > Config.inst().openingsPerSide())
				tmpConf[i - Config.inst().openingsPerSide()] = tileConf[i];
			else
				tmpConf[i + tileConf.length - Config.inst().openingsPerSide()] = tileConf[i];
		}
		
		this.tileConf = tmpConf;
		updatePathes();
	}
}
