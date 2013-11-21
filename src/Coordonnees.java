
public class Coordonnees {
	private int x;
	private int y;
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int _x){
		this.x = _x;
	}
	
	public void setY(int _y){
		this.y = _y;
	}

	public String toString() {
		String str;
		str = "";
		str += "X : " + this.getX();
		str += "Y : " + this.getY();

		return str;
	}
}
