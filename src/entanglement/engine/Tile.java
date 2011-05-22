package entanglement.engine;

import entanglement.utils.Config;

public class Tile {
	
	private int[] tileConf = null;
	private Path[] paths = null;
	
	public Tile(int[] tileConf){
		this.tileConf = tileConf;
		paths = new Path[Config.inst().openingsPerSide() * 2];
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
				paths[currPath] = new Path(i,tileConf[i]);
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
		
		tileConf = tmpConf;
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
		
		tileConf = tmpConf;
		updatePathes();
	}
	
	public boolean hasOpeningAt(int opening){
		for (int i = 0;i < paths.length;i++)
			if (paths[i].getStart() == opening || paths[i].getEnd() == opening)
				return true;
		
		return false;
	}
	
	public Path getPathFromOpening(int opening){
		for (int i = 0;i < paths.length;i++)
			if (paths[i].getStart() == opening || paths[i].getEnd() == opening)
				return paths[i];
		
		return (new Path(0,0));
	}
}
