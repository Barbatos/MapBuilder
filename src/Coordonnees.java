
public class Coordonnees {
	private int x;
	private int y;

	public Coordonnees() {
		this.x = 0;
		this.y = 0;
	}

	public Coordonnees(int _x, int _y) {
		this.x = _x;
		this.y = _y;
	}
	
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
