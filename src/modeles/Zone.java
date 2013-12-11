package modeles;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Zone extends Station {
	private int id;
	private String nom;
	private Color couleur;
	private Vector<Station> listeStations = new Vector<Station>();
	
	public Zone(){
		this.id = -1;
		this.nom = "Pas de nom";
		this.couleur = new Color(100, 100, 100);
	}
	
	public Zone(int _id, String _nom, Color couleur){
		this.id = _id;
		this.nom = _nom;
		this.couleur = couleur;
	}
	
	public void dessinerZone(Graphics g){
		int x[] = new int[3];
		int y[] = new int[3];
		
		for(int i = 0;i < listeStations.size();i++){
			x[0] = listeStations.elementAt(i).getX();
			y[0] = listeStations.elementAt(i).getY();
			
			for(int j = i;j < listeStations.size();j++){
				x[1] = listeStations.elementAt(j).getX();
				y[1] = listeStations.elementAt(j).getY();
				
				for(int k = j;k < listeStations.size();k++){
					x[2] = listeStations.elementAt(k).getX();
					y[2] = listeStations.elementAt(k).getY();
					
					g.setColor(this.couleur);
					g.fillPolygon(x, y, 3);
				}
			}
		}
	}
	
	public void ajouterStation(Station station) {
		listeStations.add(station);
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public Color getCouleur(){
		return this.couleur;
	}
	
	public Station getStation(int numero) {
		if (numero < listeStations.size())
			return listeStations.elementAt(numero);
		else 
			System.out.println("Erreur d'insertion de station dans la ligne !");
			return listeStations.lastElement();
	}

	public int getNumeroStation(Station station) {
		return listeStations.indexOf(station);
	}
	
	public Vector<Station> getListeStations(){
		return listeStations;
	}
	
	public void setId(int _id){
		this.id = _id;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
}
