package entanglement.utils;

import java.io.*;

public class Config {
	
	public final static boolean DEBUG = true;
	
	private String[] confLines;
	private int[][] tilesConf;
	private int nOS,oPS,tTC,bW,bH,sL,pC;
	private static Config instance;
	
	public static void debug(String message) {
		if(DEBUG)
			System.out.println(message);
	}
	
	public static Config inst() {
		if(instance == null)
			instance = new Config();
		return instance;
	}
	
	// should validate the configuration file
	public boolean load(Reader fileReader) {
		String oContent = ""; 
		try 
		{
			String str;
			BufferedReader bR = new BufferedReader(fileReader);
			while ((str = bR.readLine()) != null)
				oContent += str + ":";
			bR.close();
		}
		catch (IOException e) 
		{
			return false;
		}
		
		confLines = oContent.substring(0, oContent.length()-1).split(":");
		loadTilesConf();
		nOS = Integer.parseInt(confLines[0]);
		oPS = Integer.parseInt(confLines[1]);
		tTC = Integer.parseInt(confLines[2]);
		bW = Integer.parseInt(confLines[3 + tileTypesCount()]);
		bH = Integer.parseInt(confLines[4 + tileTypesCount()]);
		sL = Integer.parseInt(confLines[5 + tileTypesCount()]);
		pC = Integer.parseInt(confLines[6 + tileTypesCount()]);
		
		return true;
	}
	
	private void loadTilesConf(){
		int count = tileTypesCount();
		tilesConf = new int[tileTypesCount()][8];
		String[] tileConf;
		for(int i = 0;i < count;i++)
		{
			tileConf = confLines[3 + i].split(" ");
			tilesConf[i] = new int[tileConf.length];
			for (int j = 0;j < tileConf.length;j++)
				tilesConf[i][j] = Integer.parseInt(tileConf[j]);
		}
	}
	
	public int numberOfSides(){
		return nOS;
	}
	
	public int openingsPerSide(){
		return oPS;
	}
	
	public int tileTypesCount(){
		return tTC;
	}
	
	public int[] tileConf(int conf){
		return tilesConf[conf];
	}
	
	public int boardWidth(){
		return bW;
	}
	
	public int boardHeight(){
		return bH;
	}
	
	public int startLocation(){
		return sL;
	}
	
	public int playersCount(){
		return pC;
	}
}
