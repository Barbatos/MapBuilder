import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Vector;

public class Zone extends Station{
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
	
	public void insertStation(Station station) {
		listeStations.add(station);
	}

	public Station getStation(int numero) {
		if (numero < listeStations.size())
			return listeStations.elementAt(numero);
		else 
			System.out.println("Erreur d'insertion de station dans la Ligne !");
			return listeStations.lastElement();
	}

	public int getNumeroStation(Station station) {
		return listeStations.indexOf(station);
	}
	
	public void dessinerZone(Graphics g){
		int x[] = new int[3];
		int y[] = new int[3];
		
		for(int i = 0;i < listeStations.size();i++){
			x[0] = listeStations.elementAt(i).getCoordonnees().getX();
			y[0] = listeStations.elementAt(i).getCoordonnees().getY();
			
			for(int j = i;j < listeStations.size();j++){
				x[1] = listeStations.elementAt(j).getCoordonnees().getX();
				y[1] = listeStations.elementAt(j).getCoordonnees().getY();
				
				for(int k = j;k < listeStations.size();k++){
					x[2] = listeStations.elementAt(k).getCoordonnees().getX();
					y[2] = listeStations.elementAt(k).getCoordonnees().getY();
					
					g.fillPolygon(x, y, 3);
				}
			}
		}
	}
}
