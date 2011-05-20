package entanglement.engine;

public class Path {

	private int start,end;
	private boolean occupied;
	
	public Path(int start,int end){
		this.start = start;
		this.end = end;
	}
	
	public void setStart(int start){
		this.start = start;
	}
	
	public int getStart(){
		return start;
	}
	
	public void setend(int end){
		this.end = end;
	}
	
	public int getend(){
		return end;
	}
	
	public boolean isOccupied(){
		return occupied;
	}
}
