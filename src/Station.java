import java.awt.Graphics;

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
	
	public void dessinerStation(Graphics g){
		int taille = 10;
		g.fillOval(this.getCoordonnees().getX() - taille/2, this.getCoordonnees().getY() - taille/2, taille, taille);
		g.drawString(this.getNom(), this.getCoordonnees().getX() + 7, this.getCoordonnees().getY() - 3);
	}
	
	public String toString(){
		String str;
		str = "";
		str += "Vous etes a la station " + this.getNom();
		str += " numero " + this.getId();
		str += "dont les coordonnees sont : " + this.getCoordonnees();
		str += ". \n";
		
		return str;
	}
}
