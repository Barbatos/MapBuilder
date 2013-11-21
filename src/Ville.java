
public class Ville {
	private String nom;
	
	public Ville(){
		this.nom = "Pas de nom";
	}
	
	public Ville(String _nom){
		this.nom = _nom;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
}
