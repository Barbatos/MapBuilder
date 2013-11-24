import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Station extends Coordonnees {
	private Coordonnees coordonnees;
	private int id;
	private String nom;
	private Vector<MoyenTransport> listeTransports = new Vector<MoyenTransport>();
	
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
	
	public void insertTransport(MoyenTransport transport){
		listeTransports.add(transport);
	}
	
	public MoyenTransport getTransport(int numero) {
		if (numero < listeTransports.size())
			return listeTransports.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de transport dans la station !");
			return listeTransports.lastElement();
		}
	}
	
	public void dessinerStation(Graphics g){
		int taille = 10;
		g.fillOval(this.getCoordonnees().getX() - taille/2, this.getCoordonnees().getY() - taille/2, taille, taille);
		g.drawString(this.getNom(), this.getCoordonnees().getX() + 7, this.getCoordonnees().getY() - 3);
	}
	
	public void dessinerInfo(Graphics g){
		g.setColor(new Color(175, 175, 225));
		g.drawString("Station : " + this.getNom(), 1200 - 290, 20);
		g.drawString("Numéro : " + this.getId(), 1200 - 290, 40);
		g.drawString("Coordonnées : X :" + this.getCoordonnees().getX() + " Y : " + this.getCoordonnees().getY(), 1200 - 290, 60);
		g.drawString("Moyen(s) de transport : ", 1200 - 290, 80);
		for(int i = 0;i < listeTransports.size();i++){
			g.drawString("- " + this.getTransport(i).getNom(), 1200 - 160, 80 + i * 20);
		}
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
