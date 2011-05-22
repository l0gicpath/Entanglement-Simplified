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
		int ret = 0;
		
		switch(side)
		{
		case 0:
			ret = location - Config.inst().boardWidth();
		case 1:
			ret = location + 1;		
		case 2:
			ret = location + Config.inst().boardWidth();
		case 3:
			ret = location - 1;
		}
		
		if ((ret%Config.inst().boardWidth() == Config.inst().boardWidth()) || (ret%Config.inst().boardWidth() == -1))
			return -1;

		if ((ret/Config.inst().boardHeight() == Config.inst().boardHeight()) || (ret/Config.inst().boardHeight() == -1))
			return -1;
		
		return ret;
	}
}
