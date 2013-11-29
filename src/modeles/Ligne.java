package modeles;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Ligne extends Station{
	private int id;
	private String nom;
	private Color couleur;
	private MoyenTransport transport;
	private Vector<Station> listeStations = new Vector<Station>();
	
	public Ligne(){
		this.id = -1;
		this.nom = "Pas de nom";
		this.couleur = new Color(100, 100, 100);
	}
	
	public Ligne(int _id, String _nom, Color _couleur, MoyenTransport transport){
		this.id = _id;
		this.nom = _nom;
		this.couleur = _couleur;
		this.transport = transport;
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
	
	public MoyenTransport getTransport(){
		return this.transport;
	}
	
	public void setId(int _id){
		this.id = _id;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}

	public void setCouleur(Color _couleur){
		this.couleur = _couleur;
	}
	
	public void setTransport(MoyenTransport transport){
		this.transport = transport;
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
	
	public void dessinerLigne(Graphics g){
		for(int i = 0;i < listeStations.size();i++){
			if(i < listeStations.size() - 1){
				g.setColor(this.couleur);
				g.drawLine(listeStations.elementAt(i).getCoordonnees().getX(), listeStations.elementAt(i).getCoordonnees().getY(), listeStations.elementAt(i + 1).getCoordonnees().getX(), listeStations.elementAt(i + 1).getCoordonnees().getY());
			}
			
			g.setColor(new Color(0, 0, 0));
			listeStations.elementAt(i).dessinerStation(g);
		}
	}
	
	// m�thodes � tester
	
	public Vector<Station> getListeStations() {
		return listeStations;
	}

	public void setListeStations(Vector<Station> listeStations) {
		this.listeStations = listeStations;
	}
	
	public String afficherHoraire() {
		String str = "";
		
		str += " Horaires de " + this;
		
		for(int k = 0; k < this.getStation(0).getlisteHoraires().size(); k++) {
			for(int i = 0; i < this.getListeStations().size(); i++) {
			
				str += "\t " + this.getStation(i).getHoraire(k).getMinute()/24;
				str += ":" + this.getStation(0).getHoraire(k).getMinute()%24;
			
			}
			
			str += " \n";
		
		}
		return str;
	}

	public String toString() {
		String str;
		str = "";
		str += "la ligne " + this.getNom();
		str += ", d'ID : " + this.getId();
		
		for (int i = 0; i < listeStations.size(); i++) {
			str += this.getStation(i).toString();
		}
		for (int i = 0; i < this.getlisteHoraires().size(); i++) {
			for (int j = 0; i < listeStations.size(); i++) {
				str += this.getStation(j).getHoraire(i) + " /t ";
			}
			str += "\n";
		}
		str += "\n\n";

		return str;
	}
}
