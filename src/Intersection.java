
public class Intersection {

	private int id;
	private String type;
	
	public Intersection(){
		this.id = -1;
		this.type = "nul";
	}
	
	public Intersection(int id, String type){
		this.id = id;
		this.type = type;
	}
	
	public int getId(){
		return id;
	}
	
	public String getType(){
		return type;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setType(String type){
		this.type = type;
	}

}
