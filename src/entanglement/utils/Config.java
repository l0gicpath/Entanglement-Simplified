package entanglement.utils;

import java.io.*;

public class Config {

	private static String[] confLines;
	private static int[][] tilesConf;
	
	public static int load(Reader fileReader) {
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
			return 0;
		}
		confLines = oContent.substring(0, oContent.length()-2).split(":");
		loadTilesConf();
		return 1;
	}
	
	private static void loadTilesConf(){
		int count = tileTypesCount();
		String[] tileConf;
		for(int i = 0;i < count;i++)
		{
			tileConf = confLines[3 + i].split(" ");
			tilesConf[i] = new int[tileConf.length];
			for (int j = 0;j < tileConf.length;j++)
				tilesConf[i][j] = Integer.parseInt(tileConf[j]);
		}
	}
	
	public static int numberOfSides(){
		return Integer.parseInt(confLines[0]);
	}
	
	public static int openingsPerSide(){
		return Integer.parseInt(confLines[1]);
	}
	
	public static int tileTypesCount(){
		return Integer.parseInt(confLines[2]);
	}
	
	public static int[] tileConf(int conf){
		return tilesConf[conf];
	}
	
	public static int boardWidth(){
		return Integer.parseInt(confLines[3 + tileTypesCount()]);
	}
	
	public static int boardHeight(){
		return Integer.parseInt(confLines[4 + tileTypesCount()]);
	}
	
	public static int startLocation(){
		return Integer.parseInt(confLines[5 + tileTypesCount()]);
	}
	
	public static int playersCount(){
		return Integer.parseInt(confLines[6 + tileTypesCount()]);
	}
}
