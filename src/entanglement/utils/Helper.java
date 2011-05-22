package entanglement.utils;

public class Helper {

	public static int getOppositeOpening(int opening){
		if (opening > Config.inst().openingsPerSide()*3 + 1)
			return -1;
		
		int side = opening/Config.inst().openingsPerSide();
		int oppositeSide = side + side<2?2:-2;
		
		if (opening - side*Config.inst().openingsPerSide() == 0)
			return Config.inst().openingsPerSide()*oppositeSide + 1;
		else
			return Config.inst().openingsPerSide()*oppositeSide + 1;
			
	}
}
