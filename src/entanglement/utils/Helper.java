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
	
	public static int getOppositeLocation(int location,int side){
		switch(side)
		{
		case 0:
			return location - Config.inst().boardWidth();
		case 1:
			return location++;		
		case 2:
			return location + Config.inst().boardWidth();
		case 3:
			return location--;
		}
		
		return -1;
	}
}
