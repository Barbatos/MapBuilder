package modeles;
import java.awt.Graphics;
import java.util.Vector;

public class Zone extends Station {
	private int id;
	private String nom;
	private Vector<Station> listeStations = new Vector<Station>();
	
	public Zone(){
		this.id = -1;
		this.nom = "Pas de nom";
	}
	
	public Zone(int _id, String _nom){
		this.id = _id;
		this.nom = _nom;
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
					
					g.fillPolygon(x, y, 3);
				}
			}
		}
	}
	
	public void insertStation(Station station) {
		listeStations.add(station);
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
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
}
