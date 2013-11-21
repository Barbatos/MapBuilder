public class Station {
	private int id;
	private String nom;
	
	public Station(){
		this.id = -1;
		this.nom = "Sans nom";
	}
	
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
	
	public String toString(){
		return "Vous êtes à la station " + this.getNom() + " numéro " + this.getId();
	}
}
