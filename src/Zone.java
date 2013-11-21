
public class Zone {
	private int id;
	private String nom;
	
	public Zone(){
		this.id = -1;
		this.nom = "Pas de nom";
	}
	
	public Zone(int _id, String _nom){
		this.id = _id;
		this.nom = _nom;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void setId(int _id){
		this.id = _id;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
}
