import java.util.*;
public class Ligne extends Station{
	private int id;
	private String nom;
	private Vector<Station> listeStations;
	
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
		int taille = listeStations.size();
		listeStations.add(taille + 1, station);
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
		else 
			System.out.println("Erreur d'insertion de station dans la Ligne !");
			return listeStations.lastElement();
	}

	public int getNumeroStation(Station station) {
		return listeStations.indexOf(station);
	}
}
