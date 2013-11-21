public class Station {
	private int id;
	private String nom;
	
	public Station(int id, String nom){
		this.id = id;
		this.nom = nom;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
}
