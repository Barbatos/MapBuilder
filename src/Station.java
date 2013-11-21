public class Station extends Coordonnees {
	private Coordonnees coordonnees;
	private int id;
	private String nom;
	
	public Station(){
		this.coordonnees = new Coordonnees();
		this.id = -1;
		this.nom = "Sans nom";
	}
	
	public Station(Coordonnees coordonnees, int id, String nom){
		this.coordonnees = coordonnees;
		this.id = id;
		this.nom = nom;
	}
	
	public Coordonnees getCoordonnees(){
		return this.coordonnees;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void setCoordonnees(Coordonnees coordonnees){
		this.coordonnees = coordonnees;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public String toString(){
		return "Vous etes a la station " + this.getNom() + " numero " + this.getId() + "dont les coordonnees sont : " + this.getCoordonnees();
	}
}
