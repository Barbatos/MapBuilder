package modeles;

public class MoyenTransport {
	private int id;
	private String nom;
	
	public MoyenTransport(){
		this.id = -1;
		this.nom = "Pas de nom";
		this.vitesse = -1;
	}
	
	public MoyenTransport(int _id, String _nom){
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
