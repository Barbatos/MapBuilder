package controleurs;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import modeles.BaseDeDonnees;
import modeles.Ligne;
import modeles.MoyenTransport;
import modeles.Station;
import modeles.Ville;
import modeles.Zone;
import vues.CartePanel;
import vues.HorairesPanel;

public class Controleur {
	private CartePanel cartePanel;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Station> listeStations = new Vector<Station>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Ville> listeVilles = new Vector<Ville>();
	private Vector<MoyenTransport> listeMoyensTransport = new Vector<MoyenTransport>();
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private BaseDeDonnees bdd;
	private boolean clique = false;
	
	public Controleur(CartePanel _carte){
		this.cartePanel = _carte;
		this.bdd = new BaseDeDonnees("jdbc:mysql://better.call.barbatos.fr:3306/mapbuilder", "mapbuilder", "bite");
	}
	
	public void initialiser() {
		ResultSet reponseMoyenTransport;
		ResultSet reponseStation;
		ResultSet reponseLigne;
		ResultSet reponseZone;
		ResultSet reponseLigneStation;
		
		/**
		 * Initialisation des moyens de transport
		 */
		try {
			reponseMoyenTransport = this.bdd.select("SELECT * FROM moyentransport");
			
			while(reponseMoyenTransport.next()){
				listeMoyensTransport.add(new MoyenTransport(reponseMoyenTransport.getInt("id"), reponseMoyenTransport.getString("nom")));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/**
		 * Initialisation des stations
		 */
		try {
			reponseStation = this.bdd.select("SELECT * FROM station");
			
			while(reponseStation.next()){
				listeStations.add(new Station(reponseStation.getInt("id"), reponseStation.getInt("coordX"), reponseStation.getInt("coordY"), reponseStation.getString("nom")));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		/**
		 * Initialisation des lignes
		 */
		try {
			reponseLigne = this.bdd.select("SELECT l.*, lt.idTransport FROM `ligne` l INNER JOIN `ligne-transport` lt ON lt.idLigne = l.id");
			
			while(reponseLigne.next()){
				MoyenTransport tmp = getMoyenTransportId(reponseLigne.getInt("idTransport"));
				
				listeLignes.add(new Ligne(reponseLigne.getInt("id"), reponseLigne.getString("nom"), new Color(reponseLigne.getInt("couleurR"), reponseLigne.getInt("couleurG"), reponseLigne.getInt("couleurB")), tmp));		
			}
		} catch (SQLException e3){
			e3.printStackTrace();
		}
		
		/**
		 * Initialisation des relations stations/lignes
		 */
		try {
			reponseLigneStation = this.bdd.select("SELECT * FROM `station-ligne` ORDER BY idLigne, ordre");
			
			while(reponseLigneStation.next()){
				getLigneId(reponseLigneStation.getInt("idLigne")).ajouterStation(getStationId(reponseLigneStation.getInt("idStation")));
				getStationId(reponseLigneStation.getInt("idStation")).ajouterLigne(getLigneId(reponseLigneStation.getInt("idLigne")));;
			}
		} catch (SQLException e4){
			e4.printStackTrace();
		}
		
		/**
		 * Initialisation des zones
		 */
		try {
			reponseZone = this.bdd.select("SELECT * FROM zone");
			
			while(reponseZone.next()){
				listeZones.add(new Zone(reponseZone.getInt("id"), reponseZone.getString("nom")));
			}
		} catch (SQLException e5) {
			e5.printStackTrace();
		}

		this.cartePanel.setListeLignes(listeLignes);
		
		mouseListener = new MouseListener(){
			public void mouseClicked(MouseEvent event){
				verifierClicStation(event.getX(), event.getY());
				if(clique){
					verifierClicBouton(event.getX(), event.getY());
				}
				cartePanel.repaint();
			}
			
			public void mouseEntered(MouseEvent event){}			
			public void mouseExited(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mousePressed(MouseEvent event){}
		};
		
		mouseMotionListener = new MouseMotionListener(){
			public void mouseDragged(MouseEvent event){}
			public void mouseMoved(MouseEvent event){
				verifierPassageStation(event.getX(), event.getY());
				cartePanel.repaint();
			}
		};
		
		this.cartePanel.addMouseMotionListener(mouseMotionListener);
		this.cartePanel.addMouseListener(mouseListener);
	}
	
	public void verifierClicStation(int x, int y){
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getY() - 7))
			  ){
				this.cartePanel.setStationActuelle(listeStations.elementAt(i));
				clique = true;
			}
		}
	}
	
	public void verifierPassageStation(int x, int y){
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getY() - 7))
			  ){
				this.cartePanel.setStationPassageSouris(listeStations.elementAt(i));
			}
		}
	}
	
	public void verifierClicBouton(int x, int y){
		// Vérification du clic sur les boutons d'horaires
		for(int i = 0;i < this.cartePanel.getStationActuelle().getListeBoutonsHoraire().size();i++){
			if( 
				(x <= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getX() + this.cartePanel.getStationActuelle().getBoutonHoraire(i).getLargeur()) &&
				(x >= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getX()) && 
				(y <= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getY() + this.cartePanel.getStationActuelle().getBoutonHoraire(i).getHauteur()) && 
				(y >= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getY())
			){
				new HorairesPanel(this.cartePanel.getStationActuelle());
				return;
			}
		}
		
		// Vérification du clic sur les autres boutons
		for(int i = 0;i < this.cartePanel.getListeBoutons().size();i++){
			if( 
				(x <= this.cartePanel.getBouton(i).getX() + this.cartePanel.getBouton(i).getLargeur()) &&
				(x >= this.cartePanel.getBouton(i).getX()) && 
				(y <= this.cartePanel.getBouton(i).getY() + this.cartePanel.getBouton(i).getHauteur()) && 
				(y >= this.cartePanel.getBouton(i).getY())
			){
				System.out.println("yolo");
				return;
			}
		}
	}
	
	public Station getStationId(int id){
		for(int i = 0;i < listeStations.size();i++){
			if(listeStations.elementAt(i).getId() == id){
				return listeStations.elementAt(i);
			}
		}
		return null;
	}
	
	public Ligne getLigneId(int id){
		for(int i = 0;i < listeLignes.size();i++){
			if(listeLignes.elementAt(i).getId() == id){
				return listeLignes.elementAt(i);
			}
		}
		return null;
	}
	
	public MoyenTransport getMoyenTransportId(int id){
		for(int i = 0;i < listeMoyensTransport.size();i++){
			if(listeMoyensTransport.elementAt(i).getId() == id){
				return listeMoyensTransport.elementAt(i);
			}
		}
		return null;
	}
}
