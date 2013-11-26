package modeles;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Ligne extends Station{
	private int id;
	private String nom;
	private Vector<Station> listeStations = new Vector<Station>();
	
	public Ligne(){
		this.id = -1;
		this.nom = "Pas de nom";
	}
	
	public Ligne(int _id, String _nom){
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

	public void setStationDepart(Station station) {
		listeStations.add(0, station);
	}

	public void setStationArrivee(Station station) {
		listeStations.add(listeStations.size() , station);
	}

	public void insertStation(Station station, int numero) {
		if (numero < listeStations.size())
			listeStations.insertElementAt(station, numero);
		else
			System.out.println("Erreur d'insertion de station dans la Ligne !");
	}

	public Station getStation(int numero) {
		if (numero < listeStations.size())
			return listeStations.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de station dans la Ligne !");
			return listeStations.lastElement();
		}
	}

	public int getNumeroStation(Station station) {
		return listeStations.indexOf(station);
	}
	
	public void dessinerLigne(Graphics g, Color couleur){
		for(int i = 0;i < listeStations.size();i++){
			if(i < listeStations.size() - 1){
				g.setColor(couleur);
				g.drawLine(listeStations.elementAt(i).getCoordonnees().getX(), listeStations.elementAt(i).getCoordonnees().getY(), listeStations.elementAt(i + 1).getCoordonnees().getX(), listeStations.elementAt(i + 1).getCoordonnees().getY());
			}
			
			g.setColor(new Color(0, 0, 0));
			listeStations.elementAt(i).dessinerStation(g);
		}
	}

	public String toString() {
		String str;
		str = "";
		str += "La Station " + this.getNom();
		str += ", d'ID : " + this.getId();
		str += " est composee des stations suivantes : \n";
	
		for (int i = 0; i < listeStations.size(); i++) {
			if (i != 0)
				str += " et ";

			str += this.getStation(i).toString();
		}

		str += "\n\n";

		return str;
	}
}
